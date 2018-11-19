package by.itechart.warehouse.service;

import by.itechart.common.entity.Address;
import by.itechart.common.repository.AddressRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.warehouse.dto.CreateWarehouseDto;
import by.itechart.warehouse.dto.WarehouseDto;
import by.itechart.warehouse.entity.Placement;
import by.itechart.warehouse.entity.PlacementGoods;
import by.itechart.warehouse.entity.Warehouse;
import by.itechart.warehouse.repository.PlacementRepository;
import by.itechart.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private WarehouseRepository warehouseRepository;
    private PlacementRepository placementRepository;
    private PlacementGoodsRepository placementGoodsRepository;
    private AddressRepository addressRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository,
                                PlacementRepository placementRepository,
                                PlacementGoodsRepository placementGoodsRepository,
                                AddressRepository addressRepository) {
        this.warehouseRepository = warehouseRepository;
        this.placementRepository = placementRepository;
        this.placementGoodsRepository = placementGoodsRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Page<WarehouseDto> getWarehouses(long companyId, Pageable pageable) {
        Page<Warehouse> warehouses = warehouseRepository.findWarehousesByCompanyId(companyId, pageable);
        return warehouses.map(warehouse -> ObjectMapperUtils.map(warehouse, WarehouseDto.class));
    }

    @Override
    public Long saveWarehouse(CreateWarehouseDto createWarehouseDto, long companyId) {
        Address address = addressRepository.save(ObjectMapperUtils.map(createWarehouseDto.getAddress(), Address.class));
        Warehouse warehouse = ObjectMapperUtils.map(createWarehouseDto, Warehouse.class);
        warehouse.setCompany(new Company(companyId));
        warehouse.setAddress(address);

        Long id = warehouseRepository.save(warehouse).getId();
//        List<Placement> placementList = createWarehouseDto.getPlacements()
//                .stream().map(dto -> {
//                    Placement placement = ObjectMapperUtils.map(dto, Placement.class);
//                    placement.setWarehouse(new Warehouse(id));
//                    return placement;
//                }).collect(Collectors.toList());
//        placementRepository.saveAll(placementList);

        return id;
    }

    @Override
    public Long editWarehouse(WarehouseDto warehouseDto, long companyId, long warehouseId) {
        List<Placement> placementList = warehouseDto.getPlacements().stream().map((placementDto -> {
            List<PlacementGoods> placementGoodsList = placementDto.getPlacementGoodsList().stream().map((placementGoodsDto -> {
                PlacementGoods placementGoods = ObjectMapperUtils.map(placementGoodsDto, PlacementGoods.class);
                placementGoods.setPlacement(new Placement(placementDto.getId()));
                return placementGoods;
            })).collect(Collectors.toList());
            List<PlacementGoods> placementGoods = placementGoodsRepository.saveAll(placementGoodsList);
            Placement placement = ObjectMapperUtils.map(placementDto, Placement.class);
            placement.setWarehouse(new Warehouse(warehouseId));
            return placement;
        })).collect(Collectors.toList());
        Warehouse one = warehouseRepository.getOne(warehouseId);
        one.setPlacements(placementList);
        return warehouseRepository.save(one).getId();
    }

    @Override
    public WarehouseDto getWarehouse(long companyId, long warehouseId) {
        Warehouse warehouse = warehouseRepository.findByCompanyIdAndId(companyId, warehouseId);
        return ObjectMapperUtils.map(warehouse, WarehouseDto.class);
    }

    @Override
    public void deleteWarehouse(long warehouseId) {
        warehouseRepository.setDeleted(warehouseId);
    }
}
