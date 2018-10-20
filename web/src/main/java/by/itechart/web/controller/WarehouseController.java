package by.itechart.web.controller;

import by.itechart.common.enums.PlacementType;
import by.itechart.warehouse.entity.Placement;
import by.itechart.warehouse.entity.Warehouse;
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
@RequestMapping("/companies/{companyId}/warehouses")
public class WarehouseController {

    @GetMapping
    public List<Warehouse> getWarehouseList(@PathVariable long companyId, Pageable pageable){
        ArrayList<Warehouse> warehouses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            warehouses.add(createWarehouse(i));
        }
        return warehouses;
    }

    @PostMapping
    public Long saveWarehouse(@PathVariable long companyId, @RequestBody Warehouse warehouse){
        Long warehouseId = new Long(14);
        return warehouseId;
    }

    @GetMapping("/{warehouseId}")
    public Warehouse getWarehouse(@PathVariable long companyId, @PathVariable long warehouseId){
        Warehouse warehouse = createWarehouse(warehouseId);
        return warehouse;
    }

    @PutMapping("/{warehouseId}")
    public Long editWarehouse(@PathVariable long companyId, @PathVariable long warehouseId){
        return warehouseId;
    }

    @DeleteMapping("/{warehouseId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWarehouse(@PathVariable long companyId, @PathVariable long warehouseId){
    }

    private Warehouse createWarehouse(long i){
        ArrayList<Placement> placements = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            Placement placement = new Placement();
            placement.setPlacementType(PlacementType.values()[new Random().nextInt(4)]);
            placements.add(placement);
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setName("warehouse" + i);
        warehouse.setPlacements(placements);
        return warehouse;
    }
}
