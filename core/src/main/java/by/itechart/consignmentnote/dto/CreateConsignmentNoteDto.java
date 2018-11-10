package by.itechart.consignmentnote.dto;

import by.itechart.carrier.entity.Carrier;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.entity.Counterparty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CreateConsignmentNoteDto {
    private Company company;
    private String number;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate shipment;
    private Counterparty counterparty;
    private Carrier carrier;
    private String vehicleNumber;
    private User creator;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate registration;
    private ConsignmentNoteType consignmentNoteType;
}
