package by.itechart.company.service;

import by.itechart.company.entity.QCompany;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

final class CompanyPredicates {
    private CompanyPredicates() {
    }

    static Predicate findExcludeFirstCompany() {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(QCompany.company.id.ne(1L));
        return predicate;
    }
}
