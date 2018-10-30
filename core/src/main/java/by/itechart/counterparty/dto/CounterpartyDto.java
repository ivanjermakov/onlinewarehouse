package by.itechart.counterparty.dto;

import by.itechart.common.entity.Address;
import by.itechart.company.entity.Company;
import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.enums.CounterpartyType;
import lombok.Data;

@Data
public class CounterpartyDto {

    private Long id;
    private Address address;
    private Company company;
    private String name;
    private CounterpartyType counterpartyType;
    private String taxNumber;

    public CounterpartyDto(Counterparty counterparty) {
        id = counterparty.getId();
        address = counterparty.getAddress();
        company = counterparty.getCompany();
        name = counterparty.getName();
        counterpartyType = counterparty.getCounterpartyType();
        taxNumber = counterparty.getTaxNumber();
    }

    public Counterparty mapToCounterparty() {
        Counterparty counterparty = new Counterparty();

        counterparty.setId(id);
        counterparty.setAddress(address);
        counterparty.setCompany(company);
        counterparty.setName(name);
        counterparty.setCounterpartyType(counterpartyType);
        setTaxNumber(taxNumber);

        return counterparty;
    }
}
