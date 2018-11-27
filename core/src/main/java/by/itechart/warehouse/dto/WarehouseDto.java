package by.itechart.warehouse.dto;

import by.itechart.common.dto.AddressDto;
import lombok.Data;

import java.util.List;

@Data
public class WarehouseDto {

    private Long id;
    private String name;
    private AddressDto address;
    private List<PlacementDto> placements;
}