package by.itechart.consignmentnote.dto;

import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsignmentNoteListDto {
    private String number;
    private String companyName;
    private LocalDate registration;
    private ConsignmentNoteType consignmentNoteType;
    private ConsignmentNoteStatus consignmentNoteStatus;
}
