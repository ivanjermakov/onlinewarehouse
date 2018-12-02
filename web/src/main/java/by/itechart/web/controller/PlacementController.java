package by.itechart.web.controller;

import by.itechart.warehouse.dto.CreatePlacementDto;
import by.itechart.warehouse.dto.PlacementDto;
import by.itechart.warehouse.service.PlacementService;
import by.itechart.writeoffact.dto.PlacementCreateWriteOffActDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}/warehouses/{warehouseId}/placements")
public class PlacementController {

    private PlacementService placementService;

    @Autowired
    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @GetMapping
    public Page<PlacementDto> getPlacementList(@PathVariable long companyId, @PathVariable long warehouseId,
                                               Pageable pageable) {
        return placementService.getPlacements(companyId, warehouseId, pageable);
    }

    @PostMapping
    public Long savePlacement(@PathVariable long companyId, @PathVariable long warehouseId,
                              @RequestBody CreatePlacementDto placement) {
        return placementService.savePlacement(placement, companyId, warehouseId);
    }

    @GetMapping("/{placementId}")
    public PlacementDto getPlacement(@PathVariable long companyId, @PathVariable long warehouseId,
                                     @PathVariable long placementId) {
        return placementService.getPlacement(companyId, warehouseId, placementId);
    }

    @PutMapping("/{placementId}")
    public Long editPlacement(@RequestBody PlacementDto placement) {
        return placementService.editPlacement(placement);
    }

    @PostMapping("/{placementId}/create-write-off-act")
    public Long saveWriteOffAct(@PathVariable long companyId, @PathVariable long warehouseId,
                                @PathVariable long placementId, @RequestBody PlacementCreateWriteOffActDto placementCreateWriteOffActDto) {
        return placementService.savePlacementWriteOffAct(companyId, warehouseId, placementId, placementCreateWriteOffActDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{placementId}")
    public void deletePlacement(@PathVariable long placementId) {
        placementService.deletePlacement(placementId);
    }
}
