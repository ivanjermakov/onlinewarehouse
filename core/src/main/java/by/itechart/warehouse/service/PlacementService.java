package by.itechart.warehouse.service;

import by.itechart.warehouse.dto.CreatePlacementDto;
import by.itechart.warehouse.dto.PlacementDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlacementService {

    Page<PlacementDto> getPlacements(long companyId, long warehouseId, Pageable pageable);

    Long savePlacement(CreatePlacementDto placementDto, long companyId, long warehouseId);

    Long editPlacement(CreatePlacementDto editDto, long companyId, long warehouseId, long placementId);

    PlacementDto getPlacement(long companyId, long warehouseId, long placementId);

    void deletePlacement(long placementId);
}