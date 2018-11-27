package by.itechart.consignmentnote.dto;

import by.itechart.carrier.dto.CarrierDto;
import by.itechart.carrier.dto.DriverDto;
import by.itechart.common.entity.User;
import by.itechart.company.dto.CompanyDto;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.dto.CounterpartyDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ConsignmentNoteDto {
    private Long id;
    private CompanyDto company;
    private String number;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate shipment;
    private CounterpartyDto counterparty;
    private CarrierDto carrier;
    private DriverDto driver;
    private String vehicleNumber;
    private User creator;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate registration;
    private List<ConsignmentNoteGoodsDto> consignmentNoteGoodsList;
    private ConsignmentNoteType consignmentNoteType;
    private ConsignmentNoteStatus consignmentNoteStatus;
    private String description;
}
