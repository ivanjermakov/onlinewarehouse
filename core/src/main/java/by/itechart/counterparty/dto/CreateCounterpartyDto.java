package by.itechart.counterparty.dto;

import by.itechart.common.dto.AddressDto;
import by.itechart.counterparty.enums.CounterpartyType;
import lombok.Data;

@Data
public class CreateCounterpartyDto {
    private AddressDto address;
    private Long companyId;
    private String name;
    private CounterpartyType counterpartyType;
    private String taxNumber;
}
