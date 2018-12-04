package by.itechart.warehouse.dto;

import by.itechart.common.dto.AddressDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class WarehouseDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    private AddressDto address;
    @NotEmpty
    private List<PlacementDto> placements;
}