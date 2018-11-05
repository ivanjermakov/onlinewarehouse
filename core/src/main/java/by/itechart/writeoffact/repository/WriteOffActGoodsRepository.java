package by.itechart.writeoffact.repository;

import by.itechart.writeoffact.entity.WriteOffActGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WriteOffActGoodsRepository extends JpaRepository<WriteOffActGoods, Long>, QuerydslPredicateExecutor<WriteOffActGoods> {

}
