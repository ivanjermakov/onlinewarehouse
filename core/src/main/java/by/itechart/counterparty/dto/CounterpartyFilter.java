package by.itechart.counterparty.dto;

import by.itechart.counterparty.enums.CounterpartyType;
import lombok.Data;

@Data
public class CounterpartyFilter {

    private String name;
    private String taxNumber;
    private CounterpartyType counterpartyType;
}
