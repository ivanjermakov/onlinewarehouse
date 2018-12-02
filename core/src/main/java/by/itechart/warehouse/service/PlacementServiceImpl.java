package by.itechart.warehouse.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.exception.NotFoundEntityException;
import by.itechart.warehouse.dto.CreatePlacementDto;
import by.itechart.warehouse.dto.PlacementDto;
import by.itechart.warehouse.dto.PlacementGoodsDto;
import by.itechart.warehouse.entity.Placement;
import by.itechart.warehouse.entity.PlacementGoods;
import by.itechart.warehouse.entity.Warehouse;
import by.itechart.warehouse.repository.PlacementGoodsRepository;
import by.itechart.warehouse.repository.PlacementRepository;
import by.itechart.writeoffact.dto.CreateWriteOffActDto;
import by.itechart.writeoffact.dto.PlacementCreateWriteOffActDto;
import by.itechart.writeoffact.service.WriteOffActService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacementServiceImpl implements PlacementService {

    private PlacementRepository placementRepository;
    private PlacementGoodsRepository placementGoodsRepository;
    private WriteOffActService writeOffActService;

    public PlacementServiceImpl(PlacementRepository placementRepository,
                                PlacementGoodsRepository placementGoodsRepository,
                                WriteOffActService writeOffActService) {
        this.placementRepository = placementRepository;
        this.placementGoodsRepository = placementGoodsRepository;
        this.writeOffActService = writeOffActService;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PlacementDto> getPlacements(long companyId, long warehouseId, Pageable pageable) {
        return placementRepository.findAll(PlacementPredicate.findByCompanyIdAndWarehouseId(companyId, warehouseId), pageable)
                .map(placement -> ObjectMapperUtils.map(placement, PlacementDto.class));
    }

    @Transactional
    @Override
    public Long savePlacement(CreatePlacementDto createPlacementDto, long companyId, long warehouseId) {
        Placement placement = ObjectMapperUtils.map(createPlacementDto, Placement.class);

        placement.setWarehouse(new Warehouse(warehouseId));
        placement.getWarehouse().setCompany(new Company(companyId));
        placement.setId(null);

        Long id = placementRepository.save(placement).getId();
//        List<PlacementGoods> placementGoodsList = createPlacementDto.getPlacementGoodsList()
//                .stream().map(dto -> {
//                    PlacementGoods placementGoods = ObjectMapperUtils.map(dto, PlacementGoods.class);
//                    placementGoods.setPlacement(new Placement(id));
//                    return placementGoods;
//                }).collect(Collectors.toList());
//        placementGoodsRepository.saveAll(placementGoodsList);

        return id;
    }

    @Transactional
    @Override
    public Long editPlacement(PlacementDto editDto) {
        Placement placement = placementRepository.getOne(editDto.getId());
        ObjectMapperUtils.map(editDto, placement);

        return placement.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public PlacementDto getPlacement(long companyId, long warehouseId, long placementId) {
        Placement placement = placementRepository
                .findOne(PlacementPredicate.findByCompanyIdAndWarehouseIdAndId(companyId, warehouseId, placementId))
                .orElseThrow(() -> new NotFoundEntityException("Placement"));

        return ObjectMapperUtils.map(placement, PlacementDto.class);
    }

    @Transactional
    @Override
    public void deletePlacement(long placementId) {
        placementRepository.setDeleted(placementId);
    }

    @Transactional
    @Override
    public Long savePlacementWriteOffAct(long companyId, long warehouseId, long placementId, PlacementCreateWriteOffActDto placementCreateWriteOffActDto) {
        //validation
        Placement placement = placementRepository.findPlacementByWarehouse_Company_IdAndWarehouse_IdAndId(companyId, warehouseId, placementId);
        boolean placementCorrect = placementCreateWriteOffActDto.getPlacementGoodsDtoList().stream().allMatch(placementWriteOffActGoodsDto -> {
            return placement.getPlacementGoodsList().stream().anyMatch(placementGoods -> {
                PlacementGoodsDto goods = placementWriteOffActGoodsDto.getPlacementGoods();
                return goods.getId().equals(placementGoods.getId()) &&
                        goods.getGoods().getId().equals(placementGoods.getGoods().getId()) &&
                        goods.getAmount().equals(placementGoods.getAmount()) &&
                        goods.getCounterpartyId().equals(placementGoods.getCounterparty().getId()) &&
                        goods.getExpirationDate().equals(placementGoods.getExpirationDate());
            });
        });
        //

        if (!placementCorrect) {
            return null;
            //Todo : some exception do here
        }

        List<PlacementGoods> placementGoodsList = placementCreateWriteOffActDto.getPlacementGoodsDtoList()
                .stream()
                .map((placementGoodsDto -> {
                    placementGoodsDto.getPlacementGoods().setAmount(placementGoodsDto.getPlacementGoods().getAmount() - placementGoodsDto.getAmount());
                    PlacementGoods placementGoods = ObjectMapperUtils.map(placementGoodsDto.getPlacementGoods(), PlacementGoods.class);
                    placementGoods.setPlacement(new Placement(placement.getId()));
                    return placementGoods;
                }))
                .collect(Collectors.toList());
        List<PlacementGoods> emptyPlacementGoodsList = placementGoodsList.stream()
                .filter(placementGoods -> placementGoods.getAmount() == 0)
                .collect(Collectors.toList());

        placementGoodsList.removeAll(emptyPlacementGoodsList);
        placementGoodsRepository.saveAll(placementGoodsList);
        placementGoodsRepository.deleteAll(emptyPlacementGoodsList);

        CreateWriteOffActDto createWriteOffActDto = placementCreateWriteOffActDto.mapToCreateWriteOffActDto();
        Long writeOffActId = writeOffActService.saveWriteOffAct(createWriteOffActDto, companyId);
        return writeOffActId;
    }
}
