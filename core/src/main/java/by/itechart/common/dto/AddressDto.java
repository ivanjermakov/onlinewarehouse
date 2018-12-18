package by.itechart.common.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddressDto {
    @NotEmpty
    private String country;
    @NotEmpty
    private String region;
    @NotEmpty
    private String locality;
}
