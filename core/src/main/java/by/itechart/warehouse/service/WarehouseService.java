package by.itechart.warehouse.service;

import by.itechart.common.dto.Pair;
import by.itechart.consignmentnote.dto.CreateConsignmentNoteDto;
import by.itechart.warehouse.dto.CreateWarehouseDto;
import by.itechart.warehouse.dto.WarehouseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {

    Page<WarehouseDto> getWarehouses(long companyId, Pageable pageable);

    Long saveWarehouse(CreateWarehouseDto createWarehouseDto, long companyId);

    Long editWarehouse(WarehouseDto warehouseDto, long companyId, long warehouseId);

    WarehouseDto getWarehouse(long companyId, long warehouseId);

    void deleteWarehouse(long placementId);

    Long editWarehouseWithConsignmentNote(Pair<WarehouseDto, CreateConsignmentNoteDto> warehouseDtoAndCreateConsignmentNoteDto, long companyId, long warehouseId);

}
