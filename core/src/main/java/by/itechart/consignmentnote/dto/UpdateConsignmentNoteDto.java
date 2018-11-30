package by.itechart.consignmentnote.dto;

import by.itechart.consignmentnote.enums.ConsignmentNoteType;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate shipment;
    private Long counterpartyId;
    private Long carrierId;
    private Long driverId;
    private String vehicleNumber;
    @JsonBackReference
    private List<ConsignmentNoteGoodsDto> consignmentNoteGoodsList;
    private ConsignmentNoteType consignmentNoteType;
    private String description;
}
