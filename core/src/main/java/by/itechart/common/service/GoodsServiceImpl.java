package by.itechart.common.service;

import by.itechart.common.dto.GoodFilter;
import by.itechart.common.dto.GoodsDto;
import by.itechart.common.entity.Goods;
import by.itechart.common.repository.GoodsRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;

    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<GoodsDto> getGoods(Long companyId, GoodFilter goodFilter, Pageable pageable) {
        return goodsRepository.findAll(GoodPredicate.findFilter(companyId, goodFilter), pageable)
                .map(good -> ObjectMapperUtils.map(good, GoodsDto.class));
    }

    @Transactional
    @Override
    public void createGoods(GoodsDto goodsDto, Long companyId) {
        Goods goods = ObjectMapperUtils.map(goodsDto, Goods.class);
        goodsRepository.save(goods);
    }

    @Transactional(readOnly = true)
    @Override
    public Integer getCost(Long goodsId, Integer amount) {
        Integer goodCost = goodsRepository.getCostById(goodsId);
        return goodCost * amount;
    }
}
