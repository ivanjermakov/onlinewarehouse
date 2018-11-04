package by.itechart.warehouse.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.warehouse.dto.CreatePlacementDto;
import by.itechart.warehouse.dto.PlacementDto;
import by.itechart.warehouse.entity.Placement;
import by.itechart.warehouse.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlacementServiceImpl implements PlacementService {

    private PlacementRepository placementRepository;

    @Autowired
    public PlacementServiceImpl(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PlacementDto> getPlacements(long companyId, long warehouseId, Pageable pageable) {
        Page<Placement> placements = placementRepository
                .findAll(PlacementPredicate.findByCompanyIdAndWarehouseId(companyId, warehouseId), pageable);
        return placements.map(placement -> ObjectMapperUtils.map(placement, PlacementDto.class));
    }

    @Override
    public PlacementDto savePlacement(CreatePlacementDto createPlacementDto, long companyId, long warehouseId) {
        Placement placement = ObjectMapperUtils.map(createPlacementDto, Placement.class);

        placement.getWarehouse().setId(warehouseId);
        placement.getWarehouse().getCompany().setId(companyId);
        placement = placementRepository.save(placement);
        // CHECK!!!

        return ObjectMapperUtils.map(placement, PlacementDto.class);
    }

    @Override
    public PlacementDto editPlacement(CreatePlacementDto editDto, long companyId, long warehouseId, long placementId) {
        Placement placement = placementRepository.findPlacementById(placementId);
        ObjectMapperUtils.map(editDto, placement);
        placement = placementRepository.save(placement);
        // CHECK!!!

        return ObjectMapperUtils.map(placement, PlacementDto.class);
    }

    @Override
    public PlacementDto getPlacement(long companyId, long warehouseId, long placementId) {
        Placement placement = placementRepository
                .findOne(PlacementPredicate.findByCompanyIdAndWarehouseIdAndId(companyId, warehouseId, placementId)).orElse(null);
        return ObjectMapperUtils.map(placement, PlacementDto.class);
    }

    @Override
    public void deletePlacement(long placementId) {
        placementRepository.setDeleted(placementId);
        // correct with one param?
    }
}
