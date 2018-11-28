package by.itechart.counterparty.dto;

import by.itechart.common.dto.AddressDto;
import by.itechart.counterparty.enums.CounterpartyType;
import lombok.Data;

@Data
public class CounterpartyDto {
    private Long id;
    private AddressDto address;
    private String name;
    private CounterpartyType counterpartyType;
    private String taxNumber;
}
