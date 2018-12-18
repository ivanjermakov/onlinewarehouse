package by.itechart.consignmentnote.service;

import by.itechart.consignmentnote.dto.*;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface ConsignmentNoteService {

    Page<ConsignmentNoteListDto> getConsignmentNotes(long companyId, ConsignmentNoteFilter consignmentNoteFilter, Pageable pageable);

    ConsignmentNoteDto getConsignmentNote(long companyId, long consignmentNoteId);

    Long saveConsignmentNote(@Valid CreateConsignmentNoteDto consignmentNote, long companyId);

    Long updateConsignmentNote(@Valid UpdateConsignmentNoteDto consignmentNote, long companyId);

    Long setConsignmentNoteStatus(long consignmentNoteId, ConsignmentNoteStatus consignmentNoteStatus, long companyId);

}
