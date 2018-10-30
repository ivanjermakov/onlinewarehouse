package by.itechart.commoditylot.repository;

import by.itechart.commoditylot.entity.CommodityLotGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CommodityLotGoodsRepository extends JpaRepository<CommodityLotGoods, Long>, QuerydslPredicateExecutor<CommodityLotGoods> {
}
