package by.itechart.warehouse.service;

import by.itechart.warehouse.dto.WarehouseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {

    Page<WarehouseDto> getWarehouses(Long companyId, Pageable pageable);

    Long saveWarehouse(String name, Long companyId);

    WarehouseDto getWarehouse(Long warehouseId, Long companyId);

}
