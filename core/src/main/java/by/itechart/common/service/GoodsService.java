package by.itechart.common.service;

import by.itechart.common.dto.GoodsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoodsService {

    Page<GoodsDto> getGoods(Pageable pageable, Long companyId);

    void createGoods(GoodsDto goodsDto, Long companyId);

    Integer getCost(Long goodsId, Integer amount);

}
