package by.itechart.common.service;

import by.itechart.common.dto.UserFilter;
import by.itechart.common.entity.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

final class UserPredicate {

    static Predicate findFilter(long companyId, UserFilter userFilter) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (userFilter.getFirstname() != null) {
            predicate.and(QUser.user.firstname.containsIgnoreCase(userFilter.getFirstname()));
        }
        if (userFilter.getLastname() != null) {
            predicate.and(QUser.user.lastname.containsIgnoreCase(userFilter.getLastname()));
        }
        predicate.and(QUser.user.company.id.eq(companyId));
        predicate.and(QUser.user.deleted.isNull());

        return predicate;
    }
}
