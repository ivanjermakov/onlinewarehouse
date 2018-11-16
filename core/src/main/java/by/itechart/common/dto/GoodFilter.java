package by.itechart.common.dto;

import by.itechart.common.enums.PlacementType;
import lombok.Data;

@Data
public class GoodFilter {
    private String name;
    private PlacementType placementType;
    private Integer costFrom;
    private Integer costTo;
}
