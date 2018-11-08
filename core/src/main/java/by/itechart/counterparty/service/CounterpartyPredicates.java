package by.itechart.counterparty.service;

import by.itechart.counterparty.dto.CounterpartyFilter;
import by.itechart.counterparty.entity.QCounterparty;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;

final class CounterpartyPredicates {
    private CounterpartyPredicates() {
    }

    static Predicate findFilter(CounterpartyFilter filter, Long companyId) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (StringUtils.isNotBlank(filter.getName())) {
            predicate.and(QCounterparty.counterparty.name.startsWithIgnoreCase(filter.getName()));
        }
        if (StringUtils.isNotBlank(filter.getTaxNumber())) {
            predicate.and(QCounterparty.counterparty.taxNumber.containsIgnoreCase(filter.getTaxNumber()));
        }
        if (filter.getCounterpartyType() != null) {
            predicate.and(QCounterparty.counterparty.counterpartyType.eq(filter.getCounterpartyType()));
        }
        predicate.and(QCounterparty.counterparty.deleted.isNull());
        predicate.and(QCounterparty.counterparty.company.id.eq(companyId));
        return predicate;
    }
}
