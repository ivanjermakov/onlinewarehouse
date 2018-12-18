package by.itechart.common.service;

import by.itechart.common.dto.GoodFilter;
import by.itechart.common.entity.QGoods;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

final class GoodPredicate {

    static Predicate findFilter(long companyId, GoodFilter goodFilter) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (goodFilter.getName() != null) {
            predicate.and(QGoods.goods.name.containsIgnoreCase(goodFilter.getName()));
        }
        if (goodFilter.getPlacementType() != null) {
            predicate.and(QGoods.goods.placementType.eq(goodFilter.getPlacementType()));
        }
        if (goodFilter.getCostFrom() != null) {
            predicate.and(QGoods.goods.cost.goe(goodFilter.getCostFrom()));
        }
        if (goodFilter.getCostTo() != null) {
            predicate.and(QGoods.goods.cost.loe(goodFilter.getCostTo()));
        }

        predicate.and(QGoods.goods.company.id.eq(companyId));
        predicate.and(QGoods.goods.deleted.isNull());

        return predicate;
    }
}
