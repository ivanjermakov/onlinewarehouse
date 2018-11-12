package by.itechart.web.controller;

import by.itechart.common.enums.PlacementType;
import by.itechart.warehouse.dto.CreateWarehouseDto;
import by.itechart.warehouse.dto.WarehouseDto;
import by.itechart.warehouse.entity.Placement;
import by.itechart.warehouse.entity.Warehouse;
import by.itechart.warehouse.service.PlacementService;
import by.itechart.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/companies/{companyId}/warehouses")
public class WarehouseController {

    private WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public Page<WarehouseDto> getWarehouseList(@PathVariable long companyId, Pageable pageable) {
        return warehouseService.getWarehouses(companyId, pageable);
    }

    @PostMapping
    public Long saveWarehouse(@PathVariable long companyId, @RequestBody CreateWarehouseDto warehouse) {
        return warehouseService.saveWarehouse(warehouse, companyId);
    }

    @GetMapping("/{warehouseId}")
    public WarehouseDto getWarehouse(@PathVariable long companyId, @PathVariable long warehouseId) {
        return warehouseService.getWarehouse(companyId, warehouseId);
    }

    @PutMapping("/{warehouseId}")
    public Long editWarehouse(@PathVariable long companyId, @PathVariable long warehouseId,
                              @RequestBody CreateWarehouseDto warehouse) {
        return warehouseService.editWarehouse(warehouse, companyId, warehouseId);
    }

    @DeleteMapping("/{warehouseId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWarehouse(@PathVariable long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
    }
}
