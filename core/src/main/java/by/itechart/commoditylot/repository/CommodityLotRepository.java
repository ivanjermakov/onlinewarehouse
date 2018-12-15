package by.itechart.commoditylot.repository;

import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotStatus;
import by.itechart.commoditylot.enums.CommodityLotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommodityLotRepository extends JpaRepository<CommodityLot, Long>, QuerydslPredicateExecutor<CommodityLot> {

    Optional<CommodityLot> findByCompanyIdAndId(Long companyId, Long commodityLotId);

    @Modifying
    @Query("update CommodityLot cl set cl.commodityLotStatus = ?1 where cl.id = ?2 and cl.company.id = ?3")
    void changeCommodityLotStatus(CommodityLotStatus status, long commodityLotId, long companyId);

    List<CommodityLot> getAllByCommodityLotType(CommodityLotType commodityLotType);

    @Query(value = "select " +
            "       cp.name as counterpartyName, " +
            "       cp.tax_number as counterpartyTaxNumber, " +
            "       g.name as goodsName, " +
            "       g.placement_type as goodsPlacementType, " +
            "       g.measurement_unit_type as goodsMeasurementUnitType, " +
            "       clg.amount as amount, " +
            "       g.cost as cost, " +
            "       g.weight as weight, " +
            "       g.labelling as labelling, " +
            "       g.description as description " +
            "from " +
            "     commodity_lot cl " +
            "inner join " +
            "         commodity_lot_goods clg " +
            "         on cl.id = clg.commodity_lot_id " +
            "inner join" +
            "         goods g" +
            "         on clg.goods_id = g.id " +
            "inner join" +
            "         counterparty cp" +
            "         on cl.counterparty_id = cp.id " +
            "where " +
            "    cl.company_id = :companyId " +
            "  and " +
            "    cl.commodity_lot_type = 'IN' " +
            "  and " +
            "    cl.commodity_lot_status = 'PROCESSED' " +
            "  and " +
            "    cl.creation between :dateFrom and :dateTo " +
            "order by creation asc ",
            nativeQuery = true)
    List<InputGoodsStatistics> getInputGoodsStatistics(@Param("companyId") Long companyId,
                                                       @Param("dateFrom") LocalDate from,
                                                       @Param("dateTo") LocalDate to);
}
