package by.itechart.consignmentnote.dto;

import by.itechart.carrier.dto.CarrierDto;
import by.itechart.carrier.dto.DriverDto;
import by.itechart.company.dto.CompanyDto;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.dto.CounterpartyDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class UpdateConsignmentNoteDto {
    private Long id;
    private String number;
    private CompanyDto company;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate shipment;
    private CounterpartyDto counterparty;
    private CarrierDto carrier;
    private DriverDto driver;
    private Long creatorId;
    private String vehicleNumber;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate registration;
    @JsonBackReference
    private List<ConsignmentNoteGoodsDto> consignmentNoteGoodsList;
    private ConsignmentNoteType consignmentNoteType;
    private ConsignmentNoteStatus consignmentNoteStatus;
    private String description;
}
