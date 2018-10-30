package by.itechart.consignmentnote.service;

import by.itechart.consignmentnote.entity.QConsignmentNote;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.time.LocalDate;

public class ConsignmentNotePedicate {
    static Predicate findFilter(long companyId, ConsignmentNoteType consignmentNoteType,
                                LocalDate from, LocalDate to) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (consignmentNoteType != null) {
            predicate.and(QConsignmentNote.consignmentNote.consignmentNoteType.eq(consignmentNoteType));
        }
        if (from != null) {
            predicate.and(QConsignmentNote.consignmentNote.registration.after(from));
        }
        if (to != null) {
            predicate.and(QConsignmentNote.consignmentNote.registration.before(to));
        }
        QConsignmentNote.consignmentNote.company.id.eq(companyId);

        return predicate;
    }
}
