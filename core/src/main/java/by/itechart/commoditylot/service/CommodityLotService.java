package by.itechart.commoditylot.service;

import by.itechart.commoditylot.dto.CommodityLotDto;
import by.itechart.commoditylot.dto.CommodityLotFilter;
import by.itechart.commoditylot.dto.CommodityLotListDto;
import by.itechart.commoditylot.dto.CreateCommodityLotDto;
import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotStatus;
import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.commoditylot.repository.InputGoodsStatistics;
import by.itechart.reports.dto.ReportDateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface CommodityLotService {

    Page<CommodityLotListDto> getCommodityLots(Long companyId, Pageable pageable, CommodityLotFilter commodityLotFilter);

    Long saveCommodityLot(@Valid CreateCommodityLotDto createCommodityLotDto, Long companyId);

    CommodityLotDto getCommodityLot(Long commodityLotId, Long companyId);

    CommodityLot setCommodityLotStatus(long commodityLotId, long companyId, CommodityLotStatus status);

    List<CommodityLot> getAllByCommodityLotType(CommodityLotType commodityLotType);

    List<InputGoodsStatistics> getIncomeStatistics(Long companyId, ReportDateFilter filter);
}
