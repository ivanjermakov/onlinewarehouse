package by.itechart.warehouse.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.exception.NotFoundEntityException;
import by.itechart.warehouse.dto.CreatePlacementDto;
import by.itechart.warehouse.dto.PlacementDto;
import by.itechart.warehouse.entity.Placement;
import by.itechart.warehouse.entity.Warehouse;
import by.itechart.warehouse.repository.PlacementGoodsRepository;
import by.itechart.warehouse.repository.PlacementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlacementServiceImpl implements PlacementService {

    private PlacementRepository placementRepository;
    private PlacementGoodsRepository placementGoodsRepository;

    public PlacementServiceImpl(PlacementRepository placementRepository,
                                PlacementGoodsRepository placementGoodsRepository) {
        this.placementRepository = placementRepository;
        this.placementGoodsRepository = placementGoodsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PlacementDto> getPlacements(long companyId, long warehouseId, Pageable pageable) {
        return placementRepository.findAll(PlacementPredicate.findByCompanyIdAndWarehouseId(companyId, warehouseId), pageable)
                .map(placement -> ObjectMapperUtils.map(placement, PlacementDto.class));
    }

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

    @Override
    public Long editPlacement(PlacementDto editDto) {
        Placement placement = placementRepository.getOne(editDto.getId());
        ObjectMapperUtils.map(editDto, placement);

        return placement.getId();
    }

    @Override
    public PlacementDto getPlacement(long companyId, long warehouseId, long placementId) {
        Placement placement = placementRepository
                .findOne(PlacementPredicate.findByCompanyIdAndWarehouseIdAndId(companyId, warehouseId, placementId))
                .orElseThrow(() -> new NotFoundEntityException("Placement"));

        return ObjectMapperUtils.map(placement, PlacementDto.class);
    }

    @Override
    public void deletePlacement(long placementId) {
        placementRepository.setDeleted(placementId);
    }
}
