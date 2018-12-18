package by.itechart.warehouse.dto;

import by.itechart.common.dto.AddressDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateWarehouseDto {
    @NotNull
    private AddressDto address;
    @NotEmpty
    private String name;
}
