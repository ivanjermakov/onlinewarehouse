package by.itechart.consignmentnote.service;

import by.itechart.consignmentnote.dto.ConsignmentNoteFilter;
import by.itechart.consignmentnote.entity.QConsignmentNote;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.time.LocalDate;

public class ConsignmentNotePredicate {

    static Predicate findFilter(long companyId, ConsignmentNoteFilter consignmentNoteFilter) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (consignmentNoteFilter.getConsignmentNoteType() != null) {
            predicate.and(QConsignmentNote.consignmentNote.consignmentNoteType.eq(consignmentNoteFilter.getConsignmentNoteType()));
        }
        if (consignmentNoteFilter.getConsignmentNoteStatus() != null) {
            predicate.and(QConsignmentNote.consignmentNote.consignmentNoteStatus.eq(consignmentNoteFilter.getConsignmentNoteStatus()));
        }
        if (consignmentNoteFilter.getFrom() != null) {
            predicate.and(QConsignmentNote.consignmentNote.registration.after(consignmentNoteFilter.getFrom() ));
        }
        if (consignmentNoteFilter.getTo()  != null) {
            predicate.and(QConsignmentNote.consignmentNote.registration.before(consignmentNoteFilter.getTo()));
        }
        predicate.and(QConsignmentNote.consignmentNote.company.id.eq(companyId));

        return predicate;
    }
}
