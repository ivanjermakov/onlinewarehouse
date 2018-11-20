package by.itechart.consignmentnote.service;

import by.itechart.consignmentnote.dto.ConsignmentNoteDto;
import by.itechart.consignmentnote.dto.ConsignmentNoteFilter;
import by.itechart.consignmentnote.dto.ConsignmentNoteListDto;
import by.itechart.consignmentnote.dto.CreateConsignmentNoteDto;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsignmentNoteService {

    Page<ConsignmentNoteListDto> getConsignmentNotes(long companyId, ConsignmentNoteFilter consignmentNoteFilter, Pageable pageable);

    ConsignmentNoteDto getConsignmentNote(long companyId, long consignmentNoteId);

    Long saveConsignmentNote(CreateConsignmentNoteDto consignmentNote, long companyId);

    Long setConsignmentNoteStatus(long consignmentNoteId, ConsignmentNoteStatus consignmentNoteStatus, long companyId);
}
