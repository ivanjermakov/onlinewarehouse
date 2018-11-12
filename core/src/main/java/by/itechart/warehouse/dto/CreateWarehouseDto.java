package by.itechart.warehouse.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateWarehouseDto {
    private List<PlacementDto> placements;
    private String name;
}
