package by.itechart.consignmentnote.service;

import by.itechart.consignmentnote.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsignmentNoteService {

    Page<ConsignmentNoteListDto> getConsignmentNotes(long companyId, ConsignmentNoteFilter consignmentNoteFilter, Pageable pageable);

    ConsignmentNoteDto getConsignmentNote(long companyId, long consignmentNoteId);

    Long saveConsignmentNote(CreateConsignmentNoteDto consignmentNote, long companyId);

    Long updateConsignmentNote(UpdateConsignmentNoteDto consignmentNote, long companyId);
}
