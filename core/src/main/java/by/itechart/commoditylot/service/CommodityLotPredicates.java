package by.itechart.commoditylot.service;

import by.itechart.commoditylot.dto.CommodityLotFilter;
import by.itechart.commoditylot.entity.QCommodityLot;
import by.itechart.commoditylot.entity.QCommodityLotGoods;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;

final class CommodityLotPredicates {

    private CommodityLotPredicates() {
    }

    static Predicate findFilter(CommodityLotFilter filter, Long companyId) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (StringUtils.isNotBlank(filter.getCounterpartyName())) {
            predicate.and(QCommodityLot.commodityLot.counterparty.name.startsWithIgnoreCase(filter.getCounterpartyName()));
        }
        if (filter.getCommodityLotType() != null) {
            predicate.and(QCommodityLot.commodityLot.commodityLotType.eq(filter.getCommodityLotType()));
        }
        if (filter.getCommodityLotStatus() != null) {
            predicate.and(QCommodityLot.commodityLot.commodityLotStatus.eq(filter.getCommodityLotStatus()));
        }
        if (filter.getFrom() != null) {
            predicate.and(QCommodityLot.commodityLot.creation.after(filter.getFrom()));
        }
        if (filter.getTo() != null) {
            predicate.and(QCommodityLot.commodityLot.creation.before(filter.getTo()));
        }
        predicate.and(QCommodityLot.commodityLot.company.id.eq(companyId));
        return predicate;
    }

    static Predicate findOneByIdAndCompanyId(Long commodityLotId, Long companyId) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(QCommodityLot.commodityLot.id.eq(commodityLotId));
        predicate.and(QCommodityLot.commodityLot.company.id.eq(companyId));
        return predicate;
    }

    static Predicate findGoodsByCommodityLotId(Long commodityLotId) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(QCommodityLotGoods.commodityLotGoods.commodityLot.id.eq(commodityLotId));
        return predicate;
    }
}
