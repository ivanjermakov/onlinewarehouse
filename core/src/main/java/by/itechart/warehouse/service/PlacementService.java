package by.itechart.warehouse.service;

import by.itechart.warehouse.dto.CreatePlacementDto;
import by.itechart.warehouse.dto.PlacementDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface PlacementService {

    Page<PlacementDto> getPlacements(long companyId, long warehouseId, Pageable pageable);

    Long savePlacement(@Valid CreatePlacementDto placementDto, long companyId, long warehouseId);

    Long editPlacement(@Valid PlacementDto editDto);

    PlacementDto getPlacement(long companyId, long warehouseId, long placementId);

    void deletePlacement(long placementId);

}