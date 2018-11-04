package by.itechart.counterparty.dto;

import by.itechart.common.entity.Address;
import by.itechart.company.dto.CompanyDto;
import by.itechart.counterparty.enums.CounterpartyType;
import lombok.Data;

@Data
public class CounterpartyDto {

    private Long id;
    private Address address;
    private CompanyDto company;
    private String name;
    private CounterpartyType counterpartyType;
    private String taxNumber;
}
