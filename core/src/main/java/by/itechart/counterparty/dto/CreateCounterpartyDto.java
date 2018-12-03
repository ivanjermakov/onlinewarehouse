package by.itechart.counterparty.dto;

import by.itechart.common.dto.AddressDto;
import by.itechart.counterparty.enums.CounterpartyType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateCounterpartyDto {
    @NotNull
    private AddressDto address;
    @NotNull
    private Long companyId;
    @NotEmpty
    private String name;
    @NotNull
    private CounterpartyType counterpartyType;
    @NotEmpty
    private String taxNumber;
}
