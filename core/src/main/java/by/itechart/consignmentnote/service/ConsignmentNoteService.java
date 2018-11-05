package by.itechart.consignmentnote.service;

import by.itechart.consignmentnote.dto.ConsignmentNoteDto;
import by.itechart.consignmentnote.dto.ConsignmentNoteFilter;
import by.itechart.consignmentnote.dto.CreateConsignmentNoteDto;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ConsignmentNoteService {

    Page<ConsignmentNoteDto> getConsignmentNotes(ConsignmentNoteFilter consignmentNoteFilter, Pageable pageable);

    ConsignmentNoteDto getConsignmentNote(long companyId, long consignmentNoteId);

    ConsignmentNoteDto saveConsignmentNote(CreateConsignmentNoteDto consignmentNote, long companyId);
}
