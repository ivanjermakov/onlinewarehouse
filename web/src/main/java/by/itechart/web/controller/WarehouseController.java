package by.itechart.web.controller;

import by.itechart.common.dto.Pair;
import by.itechart.consignmentnote.dto.CreateConsignmentNoteDto;
import by.itechart.warehouse.dto.CreateWarehouseDto;
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

    private WarehouseService warehouseService;
    private WebSocketController webSocketController;

    @Autowired
    public WarehouseController(WarehouseService warehouseService, WebSocketController webSocketController) {
        this.warehouseService = warehouseService;
        this.webSocketController = webSocketController;
    }

    @GetMapping
    public Page<WarehouseDto> getWarehouseList(@PathVariable long companyId, Pageable pageable) {
        System.out.println(1);
        return warehouseService.getWarehouses(companyId, pageable);
    }

    @PostMapping
    public Long saveWarehouse(@PathVariable long companyId, @RequestBody CreateWarehouseDto warehouse) {
        Long id = warehouseService.saveWarehouse(warehouse, companyId);
        webSocketController.processManagerWarehouse(companyId);
        return id;
    }

    @GetMapping("/{warehouseId}")
    public WarehouseDto getWarehouse(@PathVariable long companyId, @PathVariable long warehouseId) {
        return warehouseService.getWarehouse(companyId, warehouseId);
    }

    @PutMapping("/{warehouseId}")
    public Long editWarehouse(@PathVariable long companyId, @PathVariable long warehouseId,
                              @RequestBody WarehouseDto warehouseDto) {
        return warehouseService.editWarehouse(warehouseDto, companyId, warehouseId);
    }

    @PostMapping("/{warehouseId}/create-consignment-note")
    public Long editWarehouseWithConsignmentNote(@PathVariable long companyId, @PathVariable long warehouseId,
                                                 @RequestBody Pair<WarehouseDto, CreateConsignmentNoteDto> warehouseDtoAndCreateConsignmentNoteDto) {
        return warehouseService.editWarehouseWithConsignmentNote(warehouseDtoAndCreateConsignmentNoteDto, companyId, warehouseId);
    }

    @DeleteMapping("/{warehouseId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWarehouse(@PathVariable long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
    }
}
