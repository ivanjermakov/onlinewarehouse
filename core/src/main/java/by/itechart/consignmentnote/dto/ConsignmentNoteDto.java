package by.itechart.consignmentnote.dto;

import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.entity.Driver;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.entity.ConsignmentNoteGoods;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.entity.Counterparty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class ConsignmentNoteDto {
    private long id;
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
    private List<ConsignmentNoteGoods> consignmentNoteGoodsList;
    private ConsignmentNoteType consignmentNoteType;
    private Driver driver;
    private ConsignmentNoteStatus consignmentNoteStatus;
    private String description;
}
