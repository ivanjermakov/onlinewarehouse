package by.itechart.web.controller;

import by.itechart.commoditylot.dto.CommodityLotDto;
import by.itechart.commoditylot.dto.CommodityLotFilter;
import by.itechart.commoditylot.dto.CommodityLotListDto;
import by.itechart.commoditylot.dto.CreateCommodityLotDto;
import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotStatus;
import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.commoditylot.service.CommodityLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}/commodity-lots")
public class CommodityLotController {

    private final CommodityLotService commodityLotService;
    private final WebSocketController webSocketController;

    @Autowired
    public CommodityLotController(CommodityLotService commodityLotService, WebSocketController webSocketController) {
        this.commodityLotService = commodityLotService;
        this.webSocketController = webSocketController;
    }

    @GetMapping
    public Page<CommodityLotListDto> getCommodityLot(@PathVariable long companyId,
                                                     CommodityLotFilter filter,
                                                     Pageable pageable) {
        System.out.println(filter);
        return commodityLotService.getCommodityLots(companyId, pageable, filter);
    }

    @GetMapping("/{commodityLotId}")
    public CommodityLotDto getCommodityLot(@PathVariable long companyId, @PathVariable long commodityLotId) {
        return commodityLotService.getCommodityLot(commodityLotId, companyId);
    }

    @PostMapping
    public Long saveCommodityLot(@PathVariable long companyId, @RequestBody CreateCommodityLotDto createCommodityLotDto) {
        Long id = commodityLotService.saveCommodityLot(createCommodityLotDto, companyId);
        webSocketController.processManagerCommodityLot(companyId);
        if (createCommodityLotDto.getCommodityLotType() == CommodityLotType.OUT)
            webSocketController.processDispatcherCommodityLot(companyId);
        return id;
    }

    @PutMapping("/{commodityLotId}")
    public Long getCommodityLot(@PathVariable long companyId,
                                @PathVariable long commodityLotId,
                                CommodityLotStatus status) {
        CommodityLot commodityLot = commodityLotService.setCommodityLotStatus(commodityLotId, companyId, status);
        Long id = commodityLot.getId();
        if (commodityLot.getCommodityLotType() == CommodityLotType.OUT && status == CommodityLotStatus.PROCESSED) {
            webSocketController.processManagerCommodityLotProcessed(companyId);
        }
        return id;
    }

}
