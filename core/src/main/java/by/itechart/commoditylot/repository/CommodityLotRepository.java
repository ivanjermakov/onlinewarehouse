package by.itechart.commoditylot.repository;

import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CommodityLotRepository extends JpaRepository<CommodityLot, Long>, QuerydslPredicateExecutor<CommodityLot> {

    @Modifying
    @Query("update CommodityLot cl set cl.commodityLotStatus = ?3 where cl.id = ?1 and cl.company.id = ?2")
    void changeCommodityLotStatus(long commodityLotId, long companyId, CommodityLotStatus status);
}
