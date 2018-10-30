package by.itechart.web.controller;

import by.itechart.common.enums.PlacementType;
import by.itechart.warehouse.dto.CreatePlacementDto;
import by.itechart.warehouse.dto.PlacementDto;
import by.itechart.warehouse.entity.Placement;
import by.itechart.warehouse.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/companies/{companyId}/warehouses/{warehouseId}/placements")
public class PlacementController {

    private PlacementService placementService;

    @Autowired
    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @GetMapping
    public List<PlacementDto> getPlacementList(@PathVariable long companyId, @PathVariable long warehouseId,
                                               Pageable pageable) {
        return placementService.getPlacements(companyId, warehouseId, pageable).getContent();
    }

    @PostMapping
    public PlacementDto savePlacement(@PathVariable long companyId, @PathVariable long warehouseId,
                              @RequestBody CreatePlacementDto placement) {
        return placementService.savePlacement(placement, companyId, warehouseId);
    }

    @GetMapping("/{placementId}")
    public PlacementDto getPlacement(@PathVariable long companyId, @PathVariable long warehouseId,
                                  @PathVariable long placementId) {
        return placementService.getPlacement(companyId, warehouseId, placementId);
    }

    @PutMapping("/{placementId}")
    public PlacementDto editPlacement(@PathVariable long companyId, @PathVariable long warehouseId,
                              @PathVariable long placementId, @RequestBody CreatePlacementDto placement) {
        return placementService.editPlacement(placement, companyId, warehouseId, placementId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{placementId}")
    public void deletePlacement(@PathVariable long companyId, @PathVariable long warehouseId,
                                @PathVariable long placementId) {
        placementService.deletePlacement(placementId);
    }
}
