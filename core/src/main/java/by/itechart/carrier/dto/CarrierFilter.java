package by.itechart.carrier.dto;

import by.itechart.carrier.enums.CarrierType;
import lombok.Data;

@Data
public class CarrierFilter {
    private String name;
    private String taxNumber;
    private CarrierType carrierType;
}
