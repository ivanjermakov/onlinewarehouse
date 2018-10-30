package by.itechart.commoditylot.repository;

import by.itechart.commoditylot.entity.CommodityLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CommodityLotRepository extends JpaRepository<CommodityLot, Long>, QuerydslPredicateExecutor<CommodityLot> {

}
