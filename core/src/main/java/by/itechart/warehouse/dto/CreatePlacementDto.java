package by.itechart.warehouse.dto;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import by.itechart.warehouse.entity.PlacementGoods;
import by.itechart.warehouse.entity.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class CreatePlacementDto {

    private Integer size;
    private PlacementType placementType;
    private MeasurementUnit measurementUnit;
    private Integer storageCost;
    private List<PlacementGoodsDto> placementGoodsList;
}
