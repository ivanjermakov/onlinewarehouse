package by.itechart.commoditylot.dto;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import lombok.Data;

@Data
public class CommodityLotGoodsDto {

    private Integer amount;
    private Long goodsId;
    private String goodsName;
    private PlacementType goodsPlacementType;
    private MeasurementUnit goodsMeasurementUnit;
}
