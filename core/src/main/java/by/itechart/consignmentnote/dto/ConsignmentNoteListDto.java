package by.itechart.consignmentnote.dto;

import by.itechart.company.dto.CompanyDto;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsignmentNoteListDto {
    private long id;
    private String number;
    private CompanyDto company;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate registration;
    private ConsignmentNoteType consignmentNoteType;
    private ConsignmentNoteStatus consignmentNoteStatus;
}
