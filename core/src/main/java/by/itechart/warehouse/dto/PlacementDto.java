package by.itechart.warehouse.dto;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PlacementDto {
    private Long id;
    @NotNull
    private Long warehouseId;
    @NotNull
    private Integer size;
    @NotNull
    private PlacementType placementType;
    @NotNull
    private MeasurementUnit measurementUnit;
    @NotNull
    private Integer storageCost;
    @NotEmpty
    private List<PlacementGoodsDto> placementGoodsList;
}