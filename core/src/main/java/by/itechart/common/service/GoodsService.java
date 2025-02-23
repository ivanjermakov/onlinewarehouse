package by.itechart.common.service;

import by.itechart.common.dto.GoodFilter;
import by.itechart.common.dto.GoodsDto;
import by.itechart.common.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface GoodsService {

    Page<GoodsDto> getGoods(Long companyId, GoodFilter goodFilter, Pageable pageable);

    Long createGoods(@Valid GoodsDto goodsDto, Long companyId);

    Integer getCost(Long goodsId, Integer amount);

    Page<Goods> findGoodsByNameAndDeletedIsFalse(String name, Pageable pageable);

}
