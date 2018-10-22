package by.itechart.web.controller;

import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company/{companyId}/commodity-lot")
public class CommodityLotController {

    @GetMapping
    public List<CommodityLot> getCommodityLot(@PathVariable long companyId,
                                              @RequestParam(value = "from", required = false) LocalDate from,
                                              @RequestParam(value = "to", required = false) LocalDate to) {
        List<CommodityLot> commodityLots = new ArrayList<>();
        // return list of commodityLots in this period of time
        for (int i = 0; i < 10; i++) {
            commodityLots.add(createCommodityLot(i));
        }
        return commodityLots;
    }

    @GetMapping("/{commodityLotId}")
    public CommodityLot getCommodityLot(@PathVariable long companyId, @PathVariable long commodityLotId) {
        CommodityLot commodityLot = createCommodityLot(commodityLotId);
        // return commodityLot with companyId and commodityLotId
        return commodityLot;
    }

    @PostMapping
    public Long saveCommodityLot(@PathVariable long companyId, @RequestBody CommodityLot commodityLot) {
        // save commodityLot and return generated commodityLot id
        Long id = 10L;
        return id;
    }

    private CommodityLot createCommodityLot(long id) {
        CommodityLot commodityLot = new CommodityLot();
        commodityLot.setId(id);
        commodityLot.setCommodityLotType(CommodityLotType.IN);
        return commodityLot;
    }
}
