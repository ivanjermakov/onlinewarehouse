package by.itechart.web.controller;

import by.itechart.common.enums.PlacementType;
import by.itechart.warehouse.entity.Placement;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/companies/{companyId}/warehouses/{warehouseId}/placements")
public class PlacementController {

    @GetMapping
    public List<Placement> getPlacementList(@PathVariable long companyId, @PathVariable long warehouseId,
                                            Pageable pageable) {
        ArrayList<Placement> placements = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            placements.add(createPlacement());
        }
        return placements;
    }

    @PostMapping
    public Long savePlacement(@PathVariable long companyId, @PathVariable long warehouseId,
                              @RequestBody Placement placement) {
        Long placementId = new Long(7);
        return placementId;
    }

    @GetMapping("/{placementId}")
    public Placement getPlacement(@PathVariable long companyId, @PathVariable long warehouseId,
                                  @PathVariable long placementId) {
        Placement placement = createPlacement();
        return placement;
    }

    @PutMapping("/{placementId}")
    public Long editPlacement(@PathVariable long companyId, @PathVariable long warehouseId,
                              @PathVariable long placementId, @RequestBody Placement placement) {
        return placementId;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{placementId}")
    public void deletePlacement(@PathVariable long companyId, @PathVariable long warehouseId,
                                @PathVariable long placementId){
    }

    private Placement createPlacement() {
        Placement placement = new Placement();
        placement.setPlacementType(PlacementType.values()[new Random().nextInt(4)]);
        placement.setSize(new Random().nextInt(100));
        placement.setStorageCost(new Random().nextInt(100));
        return placement;
    }
}
