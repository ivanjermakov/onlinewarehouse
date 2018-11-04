package by.itechart.common.repository;

import by.itechart.common.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GoodsRepository extends JpaRepository<Goods, Long>, QuerydslPredicateExecutor<Goods> {

    @Query("select g.cost from Goods g where g.id = ?1")
    Integer getCostById(Long id);
}
