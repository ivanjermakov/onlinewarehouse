package by.itechart.consignmentnote.dto;

import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.entity.Driver;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.entity.ConsignmentNoteGoods;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.entity.Counterparty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ConsignmentNoteDto {
    private long id;
    private Company company;
    private String number;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate shipment;
    private Counterparty counterparty;
    private Carrier carrier;
    private String vehicleNumber;
    private User creator;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate registration;
    @JsonBackReference
    private List<ConsignmentNoteGoods> consignmentNoteGoodsList;
    private ConsignmentNoteType consignmentNoteType;
    private Driver driver;
    private ConsignmentNoteStatus consignmentNoteStatus;
    private String description;
}
