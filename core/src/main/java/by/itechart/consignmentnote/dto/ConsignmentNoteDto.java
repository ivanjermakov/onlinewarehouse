package by.itechart.consignmentnote.dto;

import by.itechart.carrier.entity.Carrier;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.entity.Counterparty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsignmentNoteDto {
    private long id;
    private Company company;
    private String number;
    private LocalDate shipment;
    private Counterparty counterparty;
    private Carrier carrier;
    private String vehicleNumber;
    private User creator;
    private LocalDate registration;
    private ConsignmentNoteType consignmentNoteType;
}
