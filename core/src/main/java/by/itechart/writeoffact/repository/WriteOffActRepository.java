package by.itechart.writeoffact.repository;

import by.itechart.writeoffact.entity.WriteOffAct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WriteOffActRepository extends JpaRepository<WriteOffAct, Long>, QuerydslPredicateExecutor<WriteOffAct> {

}
