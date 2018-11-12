package by.itechart.warehouse.dto;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import lombok.Data;

@Data
public class CreatePlacementDto {

    private Long warehouseId;
    private Integer size;
    private PlacementType placementType;
    private MeasurementUnit measurementUnit;
    private Integer storageCost;
}
