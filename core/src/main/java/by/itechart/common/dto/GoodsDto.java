package by.itechart.common.dto;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@Data
public class GoodsDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    private PlacementType placementType;
    @NotNull
    private MeasurementUnit measurementUnit;
    @NotNull
    @Positive
    private Integer cost;
    @NotNull
    @Positive
    private Float weight;
    @NotEmpty
    private String labelling;
    private String description;
}
