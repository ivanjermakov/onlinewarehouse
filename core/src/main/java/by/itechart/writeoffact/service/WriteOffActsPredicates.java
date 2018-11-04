package by.itechart.writeoffact.service;

import by.itechart.writeoffact.dto.WriteOffActFilter;
import by.itechart.writeoffact.entity.QWriteOffAct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

final class WriteOffActsPredicates {
    private WriteOffActsPredicates() {
    }

    static Predicate findByWriteOffActFilter(WriteOffActFilter filter, Long companyId) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (filter.getWriteOffActType() != null) {
            predicate.and(QWriteOffAct.writeOffAct.writeOffActType.eq(filter.getWriteOffActType()));
        }
        if (filter.getFrom() != null) {
            predicate.and(QWriteOffAct.writeOffAct.creation.after(filter.getFrom())).or(QWriteOffAct.writeOffAct.creation.eq(filter.getFrom()));
        }
        if (filter.getTo() != null) {
            predicate.and(QWriteOffAct.writeOffAct.creation.before(filter.getTo())).or(QWriteOffAct.writeOffAct.creation.eq(filter.getTo()));
        }
        predicate.and(QWriteOffAct.writeOffAct.company.id.eq(companyId));
        return predicate;
    }
}
