package by.itechart.common.service;

import by.itechart.common.entity.QGoods;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

final class GoodsPredicates {

    private GoodsPredicates() {
    }

    static Predicate findNotDeletedByCompanyId(Long companyId) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(QGoods.goods.deleted.isNull());
        predicate.and(QGoods.goods.company.id.eq(companyId));
        return predicate;
    }
}
