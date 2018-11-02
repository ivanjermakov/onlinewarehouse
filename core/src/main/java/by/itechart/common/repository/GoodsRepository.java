package by.itechart.common.repository;

import by.itechart.common.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GoodsRepository extends JpaRepository<Goods, Long>, QuerydslPredicateExecutor<Goods> {
}
