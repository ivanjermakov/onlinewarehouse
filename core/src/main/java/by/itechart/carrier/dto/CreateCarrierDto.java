package by.itechart.carrier.dto;

import by.itechart.carrier.enums.CarrierType;
import by.itechart.common.dto.AddressDto;
import lombok.Data;

@Data
public class CreateCarrierDto {

    private AddressDto address;
    private String name;
    private CarrierType carrierType;
    private String taxNumber;
    private Boolean trusted;
}
