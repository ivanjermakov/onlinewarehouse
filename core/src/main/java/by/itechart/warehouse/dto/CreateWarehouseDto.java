package by.itechart.warehouse.dto;

import by.itechart.common.dto.AddressDto;
import lombok.Data;

@Data
public class CreateWarehouseDto {
    private AddressDto address;
    private String name;
}
