package by.itechart.consignmentnote.dto;

import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Data
public class UpdateConsignmentNoteDto {
    private Long id;
    @NotEmpty
    private String number;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull
    @PastOrPresent
    private LocalDate shipment;
    @NotNull
    private Long counterpartyId;
    @NotNull
    private Long carrierId;
    private Long driverId;
    @NotEmpty
    private String vehicleNumber;
    @JsonBackReference
    @NotEmpty
    private List<ConsignmentNoteGoodsDto> consignmentNoteGoodsList;
    @NotNull
    private ConsignmentNoteType consignmentNoteType;
    private String description;
}
