package by.itechart.warehouse.service;

import by.itechart.common.dto.Pair;
import by.itechart.common.entity.Address;
import by.itechart.common.repository.AddressRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.dto.CreateConsignmentNoteDto;
import by.itechart.consignmentnote.service.ConsignmentNoteService;
import by.itechart.profit.dto.PaymentActDto;
import by.itechart.profit.service.PaymentService;
import by.itechart.warehouse.dto.CreateWarehouseDto;
import by.itechart.warehouse.dto.WarehouseDto;
import by.itechart.warehouse.entity.Placement;
import by.itechart.warehouse.entity.PlacementGoods;
import by.itechart.warehouse.entity.Warehouse;
import by.itechart.warehouse.repository.PlacementGoodsRepository;
import by.itechart.warehouse.repository.PlacementRepository;
import by.itechart.warehouse.repository.WarehouseElasticRepository;
import by.itechart.warehouse.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    private WarehouseRepository warehouseRepository;
    private PlacementRepository placementRepository;
    private PlacementGoodsRepository placementGoodsRepository;
    private AddressRepository addressRepository;
    private ConsignmentNoteService consignmentNoteService;
    private final WarehouseElasticRepository warehouseElasticRepository;
    private final PaymentService paymentService;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository,
                                PlacementRepository placementRepository,
                                PlacementGoodsRepository placementGoodsRepository,
                                AddressRepository addressRepository,
                                ConsignmentNoteService consignmentNoteService,
                                WarehouseElasticRepository warehouseElasticRepository,
                                PaymentService paymentService) {
        this.warehouseRepository = warehouseRepository;
        this.placementRepository = placementRepository;
        this.placementGoodsRepository = placementGoodsRepository;
        this.addressRepository = addressRepository;
        this.consignmentNoteService = consignmentNoteService;
        this.warehouseElasticRepository = warehouseElasticRepository;
        this.paymentService = paymentService;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseDto> getWarehouses(long companyId, Pageable pageable) {
        return warehouseRepository.findWarehousesByCompanyId(companyId, pageable)
                .map(warehouse -> ObjectMapperUtils.map(warehouse, WarehouseDto.class));
    }

    @Transactional
    @Override
    public Long saveWarehouse(CreateWarehouseDto createWarehouseDto, long companyId) {
        Address address = addressRepository.save(ObjectMapperUtils.map(createWarehouseDto.getAddress(), Address.class));
        Warehouse warehouse = ObjectMapperUtils.map(createWarehouseDto, Warehouse.class);
        warehouse.setCompany(new Company(companyId));
        warehouse.setAddress(address);

        Long id = warehouseRepository.save(warehouse).getId();
        warehouseElasticRepository.save(warehouse);
//        List<Placement> placementList = createWarehouseDto.getPlacements()
//                .stream().map(dto -> {
//                    Placement placement = ObjectMapperUtils.map(dto, Placement.class);
//                    placement.setWarehouse(new Warehouse(id));
//                    return placement;
//                }).collect(Collectors.toList());
//        placementRepository.saveAll(placementList);

        LOGGER.info("Warehouse was created with id: {}", id);

        return id;
    }

    @Transactional
    @Override
    public Long editWarehouse(Pair<WarehouseDto, List<PaymentActDto>> warehouseDtoAndPaymentActDto, long companyId, long warehouseId) {
        paymentService.savePaymentActList(warehouseDtoAndPaymentActDto.getValue2());
        return editWarehouse(warehouseDtoAndPaymentActDto.getValue1(), companyId, warehouseId);
    }

    private Long editWarehouse(WarehouseDto warehouseDto, long companyId, long warehouseId) {
        List<Placement> placementList = warehouseDto.getPlacements().stream().map((placementDto -> {
            List<PlacementGoods> emptyPlacementGoodsList = placementDto.getPlacementGoodsList()
                    .stream()
                    .filter(placementGoodsDto -> placementGoodsDto.getAmount() == 0)
                    .map((placementGoodsDto -> {
                        PlacementGoods placementGoods = ObjectMapperUtils.map(placementGoodsDto, PlacementGoods.class);
                        placementGoods.setPlacement(new Placement(placementDto.getId()));
                        return placementGoods;
                    })).collect(Collectors.toList());
            List<PlacementGoods> placementGoodsList = placementDto.getPlacementGoodsList()
                    .stream()
                    .filter(placementGoodsDto -> placementGoodsDto.getAmount() > 0)
                    .map((placementGoodsDto -> {
                        PlacementGoods placementGoods = ObjectMapperUtils.map(placementGoodsDto, PlacementGoods.class);
                        placementGoods.setPlacement(new Placement(placementDto.getId()));
                        return placementGoods;
                    })).collect(Collectors.toList());
            placementGoodsRepository.deleteAll(emptyPlacementGoodsList);
            placementGoodsRepository.saveAll(placementGoodsList);
            Placement placement = ObjectMapperUtils.map(placementDto, Placement.class);
            placement.setWarehouse(new Warehouse(warehouseId));
            return placement;
        })).collect(Collectors.toList());
        Warehouse one = warehouseRepository.getOne(warehouseId);
        one.setPlacements(placementList);
        Long id = warehouseElasticRepository.save(one).getId();

        LOGGER.info("Edit warehouse with id: {}", id);

        return id;
    }

    @Transactional(readOnly = true)
    @Override
    public WarehouseDto getWarehouse(long companyId, long warehouseId) {
        Warehouse warehouse = warehouseRepository.findByCompanyIdAndId(companyId, warehouseId);
        return ObjectMapperUtils.map(warehouse, WarehouseDto.class);
    }

    @Transactional
    @Override
    public void deleteWarehouse(long warehouseId) {
        LOGGER.info("Delete warehouse with id: {}", warehouseId);
        warehouseRepository.setDeleted(warehouseId);
    }

    @Transactional
    @Override
    public Long editWarehouseWithConsignmentNote(Pair<WarehouseDto, CreateConsignmentNoteDto> warehouseDtoAndCreateConsignmentNoteDto, long companyId, long warehouseId) {
        editWarehouse(warehouseDtoAndCreateConsignmentNoteDto.getValue1(), companyId, warehouseId);
        return consignmentNoteService.saveConsignmentNote(warehouseDtoAndCreateConsignmentNoteDto.getValue2(), companyId);
    }

    @Transactional
    @Override
    public Page<Warehouse> findByName(String name, Pageable pageable) {
        LOGGER.info("Find warehouse by name: {}", name);
        return warehouseElasticRepository.findByNameAndCompany_IdAndDeletedIsNull(name, pageable);
    }
}
