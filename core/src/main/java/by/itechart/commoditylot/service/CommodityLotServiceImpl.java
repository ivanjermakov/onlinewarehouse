package by.itechart.commoditylot.service;

import by.itechart.commoditylot.dto.CommodityLotDto;
import by.itechart.commoditylot.dto.CommodityLotFilter;
import by.itechart.commoditylot.dto.CommodityLotListDto;
import by.itechart.commoditylot.dto.CreateCommodityLotDto;
import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.entity.CommodityLotGoods;
import by.itechart.commoditylot.enums.CommodityLotStatus;
import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.commoditylot.repository.CommodityLotGoodsRepository;
import by.itechart.commoditylot.repository.CommodityLotRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.exception.NotFoundEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommodityLotServiceImpl implements CommodityLotService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommodityLotServiceImpl.class);

    private final CommodityLotRepository commodityLotRepository;
    private final CommodityLotGoodsRepository commodityLotGoodsRepository;

    public CommodityLotServiceImpl(CommodityLotRepository commodityLotRepository, CommodityLotGoodsRepository commodityLotGoodsRepository) {
        this.commodityLotRepository = commodityLotRepository;
        this.commodityLotGoodsRepository = commodityLotGoodsRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommodityLotListDto> getCommodityLots(Long companyId, Pageable pageable, CommodityLotFilter commodityLotFilter) {
        return commodityLotRepository.findAll(CommodityLotPredicates.findFilter(commodityLotFilter, companyId), pageable)
                .map(commodityLot -> ObjectMapperUtils.map(commodityLot, CommodityLotListDto.class));
    }

    @Transactional
    @Override
    public Long saveCommodityLot(CreateCommodityLotDto createCommodityLotDto, Long companyId) {
        CommodityLot commodityLot = ObjectMapperUtils.map(createCommodityLotDto, CommodityLot.class);
        commodityLot.setId(null);
        commodityLot.setCompany(new Company(companyId));
        commodityLot.setCreation(LocalDate.now());
        commodityLot.setCommodityLotStatus(CommodityLotStatus.NOT_PROCESSED);
        Long commodityLotId = commodityLotRepository.save(commodityLot).getId();
        List<CommodityLotGoods> commodityLotGoods =
                ObjectMapperUtils.mapAll(createCommodityLotDto.getCreateCommodityLotGoodsDtoList(), CommodityLotGoods.class);
        commodityLotGoods.forEach(goods -> {
            goods.setCommodityLot(commodityLot);
            goods.setId(null);
        });
        commodityLotGoodsRepository.saveAll(commodityLotGoods);
        LOGGER.info("Commodity lot was created with id: {}", commodityLotId);

        return commodityLotId;
    }

    @Transactional(readOnly = true)
    @Override
    public CommodityLotDto getCommodityLot(Long commodityLotId, Long companyId) {
        CommodityLot commodityLot = commodityLotRepository.findByCompanyIdAndId(companyId, commodityLotId)
                .orElseThrow(() -> new NotFoundEntityException("CommodityLot"));

        return ObjectMapperUtils.map(commodityLot, CommodityLotDto.class);
    }

    @Transactional
    @Override
    public CommodityLot setCommodityLotStatus(long commodityLotId, long companyId, CommodityLotStatus status) {
        LOGGER.info("Commodity lot status change to: {}", status);
        CommodityLot commodityLot = commodityLotRepository.findByCompanyIdAndId(companyId, commodityLotId)
                .orElseThrow(() -> new NotFoundEntityException("CommodityLot"));
        commodityLotRepository.changeCommodityLotStatus(status, commodityLotId, companyId);
        return commodityLot;
    }

    @Override
    public List<CommodityLot> getAllByCommodityLotType(CommodityLotType commodityLotType) {
        return  commodityLotRepository.getAllByCommodityLotType(commodityLotType);
    }
}
