package by.itechart.carrier.service;

import by.itechart.carrier.dto.CarrierFilter;
import by.itechart.carrier.entity.QCarrier;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;

final class CarrierPredicates {
    private CarrierPredicates() {
    }

    static Predicate findFilter(CarrierFilter filter, Long companyId) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (StringUtils.isNotBlank(filter.getName())) {
            predicate.and(QCarrier.carrier.name.startsWithIgnoreCase(filter.getName()));
        }
        if (StringUtils.isNotBlank(filter.getTaxNumber())) {
            predicate.and(QCarrier.carrier.taxNumber.containsIgnoreCase(filter.getName()));
        }
        if (filter.getCarrierType() != null) {
            predicate.and(QCarrier.carrier.carrierType.eq(filter.getCarrierType()));
        }
        predicate.and(QCarrier.carrier.deleted.isNull());
        predicate.and(QCarrier.carrier.company.id.eq(companyId));
        return predicate;
    }

    static Predicate findCompanyIdAndId(Long companyId, Long carrierId) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(QCarrier.carrier.company.id.eq(companyId))
                .and(QCarrier.carrier.id.eq(carrierId))
                .and(QCarrier.carrier.deleted.isNull());
        return predicate;
    }
}
