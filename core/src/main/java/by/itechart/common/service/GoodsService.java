package by.itechart.common.service;

import by.itechart.common.dto.GoodsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GoodsService {

    Page<GoodsDto> getGoods(Pageable pageable, Long companyId);

}
