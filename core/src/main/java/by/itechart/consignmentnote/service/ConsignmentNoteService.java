package by.itechart.consignmentnote.service;

import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsignmentNoteService {

    Page<ConsignmentNote> getConsignmentNotes(ConsignmentNoteType consignmentNoteType, Pageable pageable);

    ConsignmentNote getConsignmentNote(long consignmentNoteId);

    Long saveOrUpdateConsignmentNote(ConsignmentNote consignmentNote);
}
