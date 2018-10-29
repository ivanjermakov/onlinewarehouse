package by.itechart.carrier.dto;

import by.itechart.carrier.enums.CarrierType;
import lombok.Data;

@Data
public class CreateCarrierDto {
    private String addressCountry;
    private String addressRegion;
    private String addressLocality;
    private String name;
    private CarrierType carrierType;
    private String taxNumber;
    private Boolean trusted;
}
