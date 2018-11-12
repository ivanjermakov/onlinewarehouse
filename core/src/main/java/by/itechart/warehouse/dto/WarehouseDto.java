package by.itechart.warehouse.dto;

import lombok.Data;

import java.util.List;

@Data
public class WarehouseDto {

    private Long id;
    private String name;
    private List<PlacementDto> placements;
}
