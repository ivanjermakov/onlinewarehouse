package by.itechart.writeoffact.service;

import by.itechart.writeoffact.dto.WriteOffActFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.time.LocalDate;

final class WriteOffActsPredicates {

    static Predicate findByWriteOffActFilter(WriteOffActFilter filter, Long companyId) {
        BooleanBuilder predicate = new BooleanBuilder();

//        if (filter.getWriteOffActType() != null) {
//            predicate.and(QWriteOffAct.writeOffAct.writeOffActType.eq(filter.getWriteOffActType()));
//        }
//        if (filter.getFrom() != null) {
//            predicate.and((QWriteOffAct.writeOffAct.creation.after(filter.getFrom())).or(QWriteOffAct.writeOffAct.creation.eq(filter.getFrom())));
//        }
//        if (filter.getTo() != null) {
//            predicate.and((QWriteOffAct.writeOffAct.creation.before(filter.getTo())).or(QWriteOffAct.writeOffAct.creation.eq(filter.getTo())));
//        }
//        predicate.and(QWriteOffAct.writeOffAct.company.id.eq(companyId));

        return predicate;
    }

    static Predicate findDamages(LocalDate start, LocalDate end) {
        BooleanBuilder predicate = new BooleanBuilder();

//        predicate.and(QWriteOffAct.writeOffAct.writeOffActType.eq(WriteOffActType.LOSS)
//        .or(QWriteOffAct.writeOffAct.writeOffActType.eq(WriteOffActType.SHORTFALL)));
//
//        if (start != null) {
//            predicate.and(QWriteOffAct.writeOffAct.creation.after(start).or(QWriteOffAct.writeOffAct.creation.eq(start)));
//        }
//        if (end != null) {
//            predicate.and(QWriteOffAct.writeOffAct.creation.after(end).or(QWriteOffAct.writeOffAct.creation.eq(end)));
//        }

        return predicate;
    }
}
