package by.itechart.warehouse.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.warehouse.dto.WarehouseDto;
import by.itechart.warehouse.entity.Warehouse;
import by.itechart.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WarehouseDto> getWarehouses(Long companyId, Pageable pageable) {
        Page<Warehouse> warehouses = warehouseRepository.findAllByCompany_Id(companyId, pageable);
        List<WarehouseDto> warehouseDtoList = ObjectMapperUtils.mapAll(warehouses.getContent(), WarehouseDto.class);
        return new PageImpl<>(warehouseDtoList, pageable, warehouses.getTotalElements());
    }

    @Transactional
    @Override
    public Long saveWarehouse(String name, Long companyId) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(name);
        warehouse.setCompany(new Company(companyId));
        return warehouseRepository.save(warehouse).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public WarehouseDto getWarehouse(Long warehouseId, Long companyId) {
        Warehouse warehouse = warehouseRepository.findByCompany_IdAndId(companyId, warehouseId);
        return ObjectMapperUtils.map(warehouse, WarehouseDto.class);
    }
}
