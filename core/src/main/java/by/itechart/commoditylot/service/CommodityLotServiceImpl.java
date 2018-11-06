package by.itechart.commoditylot.service;

import by.itechart.commoditylot.dto.CommodityLotDto;
import by.itechart.commoditylot.dto.CommodityLotFilter;
import by.itechart.commoditylot.dto.CommodityLotListDto;
import by.itechart.commoditylot.dto.CreateCommodityLotDto;
import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.entity.CommodityLotGoods;
import by.itechart.commoditylot.repository.CommodityLotGoodsRepository;
import by.itechart.commoditylot.repository.CommodityLotRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommodityLotServiceImpl implements CommodityLotService {

    private final CommodityLotRepository commodityLotRepository;
    private final CommodityLotGoodsRepository commodityLotGoodsRepository;

    @Autowired
    public CommodityLotServiceImpl(CommodityLotRepository commodityLotRepository, CommodityLotGoodsRepository commodityLotGoodsRepository) {
        this.commodityLotRepository = commodityLotRepository;
        this.commodityLotGoodsRepository = commodityLotGoodsRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommodityLotListDto> getCommodityLots(Long companyId, Pageable pageable, CommodityLotFilter commodityLotFilter) {
        Page<CommodityLot> commodityLots = commodityLotRepository.findAll(CommodityLotPredicates.findFilter(commodityLotFilter, companyId), pageable);
        List<CommodityLotListDto> listDtos = ObjectMapperUtils.mapAll(commodityLots.getContent(), CommodityLotListDto.class);
        return new PageImpl<>(listDtos, pageable, commodityLots.getTotalElements());
    }

    @Transactional
    @Override
    public Long saveCommodityLot(CreateCommodityLotDto createCommodityLotDto, Long companyId) {
        CommodityLot commodityLot = ObjectMapperUtils.map(createCommodityLotDto, CommodityLot.class);
        commodityLot.setId(null);
        commodityLot.setCompany(new Company(companyId));
        commodityLot.setCreation(LocalDate.now());
        Long commodityLotId = commodityLotRepository.save(commodityLot).getId();
        List<CommodityLotGoods> commodityLotGoods =
                ObjectMapperUtils.mapAll(createCommodityLotDto.getCreateCommodityLotGoodsDtoList(), CommodityLotGoods.class);
        commodityLotGoods.forEach(goods -> {
            goods.setCommodityLot(commodityLot);
            goods.setId(null);
        });
        commodityLotGoodsRepository.saveAll(commodityLotGoods);
        return commodityLotId;
    }

    @Transactional(readOnly = true)
    @Override
    public CommodityLotDto getCommodityLot(Long commodityLotId, Long companyId) {
        CommodityLot commodityLot = commodityLotRepository.getOne(commodityLotId);
        return ObjectMapperUtils.map(commodityLot, CommodityLotDto.class);
    }
}
