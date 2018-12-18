package by.itechart.commoditylot.dto;

import by.itechart.commoditylot.enums.CommodityLotType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateCommodityLotDto {
    private Long counterpartyId;
    @NotNull
    private CommodityLotType commodityLotType;
    @NotEmpty
    private List<CreateCommodityLotGoodsDto> createCommodityLotGoodsDtoList;
}
