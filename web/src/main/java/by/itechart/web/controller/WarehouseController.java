package by.itechart.web.controller;

import by.itechart.warehouse.dto.WarehouseDto;
import by.itechart.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }


    @GetMapping
    public Page<WarehouseDto> getWarehouseList(@PathVariable long companyId, Pageable pageable) {
        return warehouseService.getWarehouses(companyId, pageable);
    }

    @PostMapping
    public Long saveWarehouse(@PathVariable long companyId, @RequestBody String name) {
        return warehouseService.saveWarehouse(name, companyId);
    }

    @GetMapping("/{warehouseId}")
    public WarehouseDto getWarehouse(@PathVariable long companyId, @PathVariable long warehouseId) {
        return warehouseService.getWarehouse(warehouseId, companyId);
    }

    @PutMapping("/{warehouseId}")
    public Long editWarehouse(@PathVariable long companyId, @PathVariable long warehouseId) {
        return warehouseId;
    }

    @DeleteMapping("/{warehouseId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWarehouse(@PathVariable long companyId, @PathVariable long warehouseId) {
    }
}
