package by.itechart.commoditylot.service;

import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface CommodityLotService {

    Page<CommodityLot> getCommodityLots(Long companyId, Pageable pageable, CommodityLotType commodityLotType, LocalDate from, LocalDate to);

    Long saveCommodityLot(CommodityLot commodityLot);

    CommodityLot getCommodityLot(Long commodityLotId);
}
