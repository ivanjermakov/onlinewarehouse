package by.itechart.web.controller;

import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.commoditylot.service.CommodityLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<CommodityLot> getCommodityLot(@PathVariable long companyId,
                                              @RequestParam CommodityLotType commodityLotType,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                                              @RequestParam(required = false) Pageable pageable) {
//        List<CommodityLot> commodityLots = new ArrayList<>();
//        // return list of commodityLots in this period of time
//        for (int i = 0; i < 10; i++) {
//            commodityLots.add(createCommodityLot(i));
//        }
//        return commodityLots;
        return commodityLotService.getCommodityLots(companyId, pageable, commodityLotType, from, to).getContent();
    }

    @GetMapping("/{commodityLotId}")
    public CommodityLot getCommodityLot(@PathVariable long companyId, @PathVariable long commodityLotId) {
//        CommodityLot commodityLot = createCommodityLot(commodityLotId);
//        // return commodityLot with companyId and commodityLotId
//        return commodityLot;
        return commodityLotService.getCommodityLot(commodityLotId);
    }

    @PostMapping
    public Long saveCommodityLot(@PathVariable long companyId, @RequestBody CommodityLot commodityLot) {
//        // save commodityLot and return generated commodityLot id
//        Long id = 10L;
//        return id;
        return commodityLotService.saveCommodityLot(commodityLot);
    }

    private CommodityLot createCommodityLot(long id) {
        CommodityLot commodityLot = new CommodityLot();
        commodityLot.setId(id);
        commodityLot.setCommodityLotType(CommodityLotType.IN);
        return commodityLot;
    }
}
