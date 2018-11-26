package by.itechart.warehouse.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.warehouse.dto.CreateWarehouseDto;
import by.itechart.warehouse.dto.WarehouseDto;
import by.itechart.warehouse.entity.Placement;
import by.itechart.warehouse.entity.Warehouse;
import by.itechart.warehouse.repository.PlacementRepository;
import by.itechart.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private WarehouseRepository warehouseRepository;
    private PlacementRepository placementRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository,
                                PlacementRepository placementRepository){
        this.warehouseRepository = warehouseRepository;
        this.placementRepository = placementRepository;
    }

    @Override
    public Page<WarehouseDto> getWarehouses(long companyId, Pageable pageable) {
        Page<Warehouse> warehouses = warehouseRepository.findWarehousesByCompanyId(companyId, pageable);
        return warehouses.map(warehouse -> ObjectMapperUtils.map(warehouse, WarehouseDto.class));
    }

    @Override
    public Long saveWarehouse(CreateWarehouseDto createWarehouseDto, long companyId) {
        Warehouse warehouse = ObjectMapperUtils.map(createWarehouseDto, Warehouse.class);
        warehouse.setCompany(new Company(companyId));

        Long id = warehouseRepository.save(warehouse).getId();
        List<Placement> placementList = createWarehouseDto.getPlacements()
                .stream().map(dto -> {
                    Placement placement = ObjectMapperUtils.map(dto, Placement.class);
                    placement.setWarehouse(new Warehouse(id));
                    return placement;
                }).collect(Collectors.toList());
        placementRepository.saveAll(placementList);

        return id;
    }

    @Override
    public Long editWarehouse(CreateWarehouseDto editWarehouseDto, long companyId, long warehouseId) {
        deleteWarehouse(warehouseId); //TODO refactor!!!
        return saveWarehouse(editWarehouseDto, companyId);
    }

    @Override
    public WarehouseDto getWarehouse(long companyId, long warehouseId) {
        Warehouse warehouse = warehouseRepository.findByCompanyIdAndId(companyId, warehouseId);
        return ObjectMapperUtils.map(warehouse, WarehouseDto.class);
    }

    @Override
    public void deleteWarehouse(long warehouseId) {
        warehouseRepository.setDeleted(warehouseId);
    }
}
