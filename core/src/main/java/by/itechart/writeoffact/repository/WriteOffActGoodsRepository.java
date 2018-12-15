package by.itechart.writeoffact.repository;

import by.itechart.writeoffact.entity.WriteOffActGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WriteOffActGoodsRepository extends JpaRepository<WriteOffActGoods, Long>, QuerydslPredicateExecutor<WriteOffActGoods> {

    @Query(value = "select a.responsible_person    as responsiblePerson, " +
            "       woag.write_off_type     as writeOffType, " +
            "       g.name                  as goodsName, " +
            "       g.placement_type        as goodsPlacementType, " +
            "       g.measurement_unit_type as goodsMeasurementUnitType, " +
            "       woag.amount             as amount, " +
            "       g.cost                  as cost, " +
            "       g.weight                as weight, " +
            "       g.labelling             as labelling, " +
            "       g.description           as description " +
            "from write_off_act_goods woag " +
            "inner join goods g on woag.goods_id = g.id " +
            "inner join write_off_act a on woag.write_off_act_id = a.id " +
            "where a.company_id = :companyId " +
            "  and a.write_off_act_type = 'WAREHOUSE' " +
            "  and a.creation between :dateFrom and :dateTo", nativeQuery = true)
    List<WriteOffStatistics> getWriteOffStatistics(@Param("companyId") Long companyId,
                                                   @Param("dateFrom") LocalDate from,
                                                   @Param("dateTo") LocalDate to);
}
