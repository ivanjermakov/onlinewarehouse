package by.itechart.warehouse.repository;

import by.itechart.warehouse.entity.PlacementGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PlacementGoodsRepository extends JpaRepository<PlacementGoods, Long>, QuerydslPredicateExecutor<PlacementGoods> {
}
