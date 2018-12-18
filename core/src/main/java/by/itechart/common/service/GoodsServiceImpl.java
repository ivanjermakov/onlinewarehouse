package by.itechart.common.service;

import by.itechart.common.dto.GoodFilter;
import by.itechart.common.dto.GoodsDto;
import by.itechart.common.entity.Goods;
import by.itechart.common.repository.GoodsElasticRepository;
import by.itechart.common.repository.GoodsRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    private final GoodsRepository goodsRepository;
    private final GoodsElasticRepository goodsElasticRepository;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository, GoodsElasticRepository goodsElasticRepository) {
        this.goodsRepository = goodsRepository;
        this.goodsElasticRepository = goodsElasticRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<GoodsDto> getGoods(Long companyId, GoodFilter goodFilter, Pageable pageable) {
        return goodsRepository.findAll(GoodPredicate.findFilter(companyId, goodFilter), pageable)
                .map(good -> ObjectMapperUtils.map(good, GoodsDto.class));
    }

    @Transactional
    @Override
    public Long createGoods(GoodsDto goodsDto, Long companyId) {
        Goods goods = ObjectMapperUtils.map(goodsDto, Goods.class);
        goods.setCompany(new Company(companyId));
        goodsElasticRepository.save(goods);
        Long id = goodsRepository.save(goods).getId();

        LOGGER.info("Good was created with id: {}", id);
        return id;
    }

    @Transactional(readOnly = true)
    @Override
    public Integer getCost(Long goodsId, Integer amount) {
        Integer goodCost = goodsRepository.getCostById(goodsId);
        return goodCost * amount;
    }

    @Transactional
    @Override
    public Page<Goods> findGoodsByNameAndDeletedIsFalse(String name, Pageable pageable) {
        LOGGER.info("Find Goods by name: {}", name);
        return goodsElasticRepository.findGoodsByNameAndDeletedIsFalse(name, pageable);
    }
}
