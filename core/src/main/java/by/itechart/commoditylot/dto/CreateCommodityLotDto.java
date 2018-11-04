package by.itechart.commoditylot.dto;

import by.itechart.commoditylot.enums.CommodityLotType;
import lombok.Data;

import java.util.List;

@Data
public class CreateCommodityLotDto {

    private Long counterpartyId;
    private CommodityLotType commodityLotType;
    private List<CreateCommodityLotGoodsDto> createCommodityLotGoodsDtoList;
}
