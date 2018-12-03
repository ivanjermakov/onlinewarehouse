package by.itechart.carrier.dto;

import by.itechart.carrier.enums.CarrierType;
import by.itechart.common.dto.AddressDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateCarrierDto {
    @NotNull
    private AddressDto address;
    @NotEmpty
    private String name;
    @NotNull
    private CarrierType carrierType;
    @NotEmpty
    private String taxNumber;
    @NotNull
    private Boolean trusted;
}
