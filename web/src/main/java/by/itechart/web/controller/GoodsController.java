package by.itechart.web.controller;

import by.itechart.common.dto.GoodsDto;
import by.itechart.common.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping()
    public List<GoodsDto> getGoods(@PathVariable long companyId, Pageable pageable) {
        return goodsService.getGoods(pageable, companyId).getContent();
    }

//    @PostMapping()
//    public Long createGoods(@PathVariable long companyId){
//        return 1L;
//    }
}
