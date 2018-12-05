package by.itechart.consignmentnote.dto;

import by.itechart.carrier.dto.CarrierDto;
import by.itechart.carrier.dto.DriverDto;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.dto.CounterpartyDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreateConsignmentNoteDto {
    @NotEmpty
    private String number;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull
    @PastOrPresent
    private LocalDate shipment;
    @NotNull
    private CounterpartyDto counterparty;
    @NotNull
    private CarrierDto carrier;
    private DriverDto driver;
    @NotNull
    private Long creatorId;
    @NotEmpty
    private String vehicleNumber;
    @NotEmpty
    @JsonBackReference
    private List<ConsignmentNoteGoodsDto> consignmentNoteGoodsList;
    @NotNull
    private ConsignmentNoteType consignmentNoteType;
    private String description;
}
