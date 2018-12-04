package by.itechart.commoditylot.repository;

import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotStatus;
import by.itechart.commoditylot.enums.CommodityLotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface CommodityLotRepository extends JpaRepository<CommodityLot, Long>, QuerydslPredicateExecutor<CommodityLot> {

    Optional<CommodityLot> findByCompanyIdAndId(Long companyId, Long commodityLotId);

    @Modifying
    @Query("update CommodityLot cl set cl.commodityLotStatus = ?1 where cl.id = ?2 and cl.company.id = ?3")
    void changeCommodityLotStatus(CommodityLotStatus status, long commodityLotId, long companyId);

    List<CommodityLot> getAllByCommodityLotType(CommodityLotType commodityLotType);
}
