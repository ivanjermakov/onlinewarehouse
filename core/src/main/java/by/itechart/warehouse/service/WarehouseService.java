package by.itechart.warehouse.service;

import by.itechart.warehouse.dto.CreateWarehouseDto;
import by.itechart.warehouse.dto.WarehouseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {
    Page<WarehouseDto> getWarehouses(long companyId, Pageable pageable);

    Long saveWarehouse(CreateWarehouseDto createWarehouseDto, long companyId);

    Long editWarehouse(CreateWarehouseDto editWarehouseDto, long companyId, long warehouseId);

    WarehouseDto getWarehouse(long companyId, long warehouseId);

    void deleteWarehouse(long placementId);
}
