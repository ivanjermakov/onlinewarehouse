package by.itechart.common.dto;

import by.itechart.common.entity.Goods;
import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import lombok.Data;

@Data
public class GoodsDto {

    private Long id;
    private String name;
    private PlacementType placementType;
    private MeasurementUnit measurementUnit;
    private Integer cost;
    private Float weight;
    private String labelling;
    private String description;

    public GoodsDto(Goods goods) {
        id = goods.getId();
        name = goods.getName();
        placementType = goods.getPlacementType();
        measurementUnit = goods.getMeasurementUnit();
        cost = goods.getCost();
        weight = goods.getWeight();
        labelling = goods.getLabelling();
        description = goods.getDescription();
    }
}
