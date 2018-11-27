package by.itechart.consignmentnote.repository;

import by.itechart.consignmentnote.entity.ConsignmentNoteGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ConsignmentNoteGoodsRepository  extends JpaRepository<ConsignmentNoteGoods, Long>, QuerydslPredicateExecutor<ConsignmentNoteGoods> {

}
