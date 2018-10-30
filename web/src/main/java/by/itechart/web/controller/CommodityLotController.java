package by.itechart.web.controller;

import by.itechart.commoditylot.dto.CommodityLotDto;
import by.itechart.commoditylot.dto.CommodityLotFilter;
import by.itechart.commoditylot.dto.CommodityLotListDto;
import by.itechart.commoditylot.dto.CreateCommodityLotDto;
import by.itechart.commoditylot.service.CommodityLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/commodity-lots")
public class CommodityLotController {

    private final CommodityLotService commodityLotService;

    @Autowired
    public CommodityLotController(CommodityLotService commodityLotService) {
        this.commodityLotService = commodityLotService;
    }

    @GetMapping
    public List<CommodityLotListDto> getCommodityLot(@PathVariable long companyId,
                                                     CommodityLotFilter filter,
                                                     Pageable pageable) {
        return commodityLotService.getCommodityLots(companyId, pageable, filter).getContent();
    }

    @GetMapping("/{commodityLotId}")
    public CommodityLotDto getCommodityLot(@PathVariable long companyId, @PathVariable long commodityLotId) {
        return commodityLotService.getCommodityLot(commodityLotId, companyId);
    }

    @PostMapping
    public Long saveCommodityLot(@PathVariable long companyId, @RequestBody CreateCommodityLotDto createCommodityLotDto) {
        return commodityLotService.saveCommodityLot(createCommodityLotDto, companyId);
    }
}
