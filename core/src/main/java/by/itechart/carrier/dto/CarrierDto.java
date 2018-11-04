package by.itechart.carrier.dto;

import by.itechart.carrier.entity.Driver;
import by.itechart.carrier.enums.CarrierType;
import lombok.Data;

import java.util.List;

@Data
public class CarrierDto {

    private Long id;
    private String addressCountry;
    private String addressRegion;
    private String addressLocality;
    private String name;
    private String taxNumber;
    private CarrierType carrierType;
    private Boolean trusted;
    private List<Driver> drivers;
}
