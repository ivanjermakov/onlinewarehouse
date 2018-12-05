package by.itechart.web.controller;

import by.itechart.common.dto.GoodFilter;
import by.itechart.common.dto.GoodsDto;
import by.itechart.common.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping()
    public Page<GoodsDto> getGoods(@PathVariable long companyId,
                                   GoodFilter goodFilter,
                                   Pageable pageable) {
        return goodsService.getGoods(companyId, goodFilter, pageable);
    }

    @PostMapping()
    public Long createGoods(@PathVariable long companyId,
                            @RequestBody GoodsDto goodsDto) {
        return goodsService.createGoods(goodsDto, companyId);
    }
}
