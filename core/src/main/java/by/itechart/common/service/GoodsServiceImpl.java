package by.itechart.common.service;

import by.itechart.common.dto.GoodFilter;
import by.itechart.common.dto.GoodsDto;
import by.itechart.common.entity.Goods;
import by.itechart.common.repository.GoodsElasticRepository;
import by.itechart.common.repository.GoodsRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsServiceImpl implements GoodsService {

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
        Page<Goods> goods = goodsRepository.findAll(GoodPredicate.findFilter(companyId, goodFilter), pageable);
        return goods.map(good -> ObjectMapperUtils.map(good, GoodsDto.class));
    }

    @Transactional
    @Override
    public void createGoods(GoodsDto goodsDto, Long companyId) {
        Goods goods = ObjectMapperUtils.map(goodsDto, Goods.class);
        goodsElasticRepository.save(goods);
        goodsRepository.save(goods);

    }

    @Transactional(readOnly = true)
    @Override
    public Integer getCost(Long goodsId, Integer amount) {
        Integer costById = goodsRepository.getCostById(goodsId);
        return costById * amount;
    }

    @Transactional
    @Override
    public Page<Goods> findGoodsByNameAndDeletedIsFalse(String name, Pageable pageable) {
        return goodsElasticRepository.findGoodsByNameAndDeletedIsFalse(name, pageable);
    }
}
