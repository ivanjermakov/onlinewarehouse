package by.itechart.carrier.dto;

import by.itechart.carrier.enums.CarrierType;
import by.itechart.common.dto.AddressDto;
import lombok.Data;

import java.util.List;

@Data
public class CarrierDto {
    private Long id;
    private AddressDto address;
    private String name;
    private String taxNumber;
    private CarrierType carrierType;
    private Boolean trusted;
    private List<String> driverInfo;
}
