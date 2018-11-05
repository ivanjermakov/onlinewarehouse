package by.itechart.consignmentnote.dto;

import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsignmentNoteFilter {
    private long companyId;
    private ConsignmentNoteType consignmentNoteType;
    private ConsignmentNoteStatus consignmentNoteStatus;
    private LocalDate from;
    private LocalDate to;
}
