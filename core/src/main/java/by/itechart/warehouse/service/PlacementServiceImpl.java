package by.itechart.warehouse.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.entity.ConsignmentNoteGoods;
import by.itechart.warehouse.dto.CreatePlacementDto;
import by.itechart.warehouse.dto.PlacementDto;
import by.itechart.warehouse.dto.PlacementGoodsDto;
import by.itechart.warehouse.entity.Placement;
import by.itechart.warehouse.entity.PlacementGoods;
import by.itechart.warehouse.entity.Warehouse;
import by.itechart.warehouse.repository.PlacementGoodsRepository;
import by.itechart.warehouse.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacementServiceImpl implements PlacementService {

    private PlacementRepository placementRepository;
    private PlacementGoodsRepository placementGoodsRepository;

    @Autowired
    public PlacementServiceImpl(PlacementRepository placementRepository,
                                PlacementGoodsRepository placementGoodsRepository){
        this.placementRepository = placementRepository;
        this.placementGoodsRepository = placementGoodsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PlacementDto> getPlacements(long companyId, long warehouseId, Pageable pageable) {
        Page<Placement> placements = placementRepository
                .findAll(PlacementPredicate.findByCompanyIdAndWarehouseId(companyId, warehouseId), pageable);
        return placements.map(placement -> ObjectMapperUtils.map(placement, PlacementDto.class));
    }

    @Override
    public Long savePlacement(CreatePlacementDto createPlacementDto, long companyId, long warehouseId) {
        Placement placement = ObjectMapperUtils.map(createPlacementDto, Placement.class);

        placement.setWarehouse(new Warehouse(warehouseId));
        placement.getWarehouse().setCompany(new Company(companyId));

        Long id = placementRepository.save(placement).getId();
        List<PlacementGoods> placementGoodsList = createPlacementDto.getPlacementGoodsList()
                .stream().map(dto -> {
                    PlacementGoods placementGoods = ObjectMapperUtils.map(dto, PlacementGoods.class);
                    placementGoods.setPlacement(new Placement(id));
                    return placementGoods;
                }).collect(Collectors.toList());
        placementGoodsRepository.saveAll(placementGoodsList);

        return id;
    }

    @Override
    public Long editPlacement(CreatePlacementDto editDto, long companyId, long warehouseId, long placementId) {
        deletePlacement(placementId); //TODO refactor!!!
        return savePlacement(editDto, companyId, warehouseId);
    }

    @Override
    public PlacementDto getPlacement(long companyId, long warehouseId, long placementId) {
        Placement placement = placementRepository
                .findOne(PlacementPredicate.findByCompanyIdAndWarehouseIdAndId(companyId, warehouseId, placementId)).orElse(null);
        return ObjectMapperUtils.map(placement, PlacementDto.class);
    }

    @Override
    public void deletePlacement(long placementId) {
        placementRepository.setDeleted(placementId);
    }
}
