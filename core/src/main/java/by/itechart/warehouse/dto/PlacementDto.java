package by.itechart.warehouse.dto;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import lombok.Data;

import java.util.List;

@Data
public class PlacementDto {
    private Long id;
    private Long warehouseId;
    private Integer size;
    private PlacementType placementType;
    private MeasurementUnit measurementUnit;
    private Integer storageCost;
    private List<PlacementGoodsDto> placementGoodsList;
}