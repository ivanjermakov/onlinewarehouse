package by.itechart.warehouse.service;


import by.itechart.warehouse.entity.QPlacement;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

final class PlacementPredicate {

    static Predicate findByCompanyIdAndWarehouseId(long companyId, long warehouseId) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(QPlacement.placement.warehouse.company.id.eq(companyId))
                .and(QPlacement.placement.warehouse.id.eq(warehouseId))
                .and(QPlacement.placement.deleted.isNull());

        return predicate;
    }

    static Predicate findByCompanyIdAndWarehouseIdAndId(long companyId, long warehouseId, long placementId) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(QPlacement.placement.warehouse.company.id.eq(companyId))
                .and(QPlacement.placement.warehouse.id.eq(warehouseId))
                .and(QPlacement.placement.id.eq(placementId))
                .and(QPlacement.placement.deleted.isNull());

        return predicate;
    }
}
