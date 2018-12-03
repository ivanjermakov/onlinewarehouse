package by.itechart.warehouse.service;

import by.itechart.common.dto.Pair;
import by.itechart.consignmentnote.dto.CreateConsignmentNoteDto;
import by.itechart.warehouse.dto.CreateWarehouseDto;
import by.itechart.warehouse.dto.WarehouseDto;
import by.itechart.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface WarehouseService {

    Page<WarehouseDto> getWarehouses(long companyId, Pageable pageable);

    Long saveWarehouse(@Valid CreateWarehouseDto createWarehouseDto, long companyId);

    Long editWarehouse(@Valid WarehouseDto warehouseDto, long companyId, long warehouseId);

    WarehouseDto getWarehouse(long companyId, long warehouseId);

    void deleteWarehouse(long placementId);

    Page<Warehouse> findByName(String name, Pageable pageable);

    Long editWarehouseWithConsignmentNote(Pair<WarehouseDto, CreateConsignmentNoteDto> warehouseDtoAndCreateConsignmentNoteDto, long companyId, long warehouseId);

}
