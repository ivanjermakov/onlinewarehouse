package by.itechart.warehouse.dto;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import by.itechart.warehouse.entity.PlacementGoods;
import by.itechart.warehouse.entity.Warehouse;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PlacementDto {

    private long id;
    private Warehouse warehouse;
    private Integer size;
    private PlacementType placementType;
    private MeasurementUnit measurementUnit;
    private Integer storageCost;
    private List<PlacementGoods> placementGoodsList;
    private LocalDate deleted;
}
