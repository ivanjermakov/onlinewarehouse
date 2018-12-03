package by.itechart.commoditylot.service;

import by.itechart.commoditylot.dto.CommodityLotDto;
import by.itechart.commoditylot.dto.CommodityLotFilter;
import by.itechart.commoditylot.dto.CommodityLotListDto;
import by.itechart.commoditylot.dto.CreateCommodityLotDto;
import by.itechart.commoditylot.enums.CommodityLotStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface CommodityLotService {

    Page<CommodityLotListDto> getCommodityLots(Long companyId, Pageable pageable, CommodityLotFilter commodityLotFilter);

    Long saveCommodityLot(@Valid CreateCommodityLotDto createCommodityLotDto, Long companyId);

    CommodityLotDto getCommodityLot(Long commodityLotId, Long companyId);

    Long setCommodityLotStatus(long commodityLotId, long companyId, CommodityLotStatus status);

}
