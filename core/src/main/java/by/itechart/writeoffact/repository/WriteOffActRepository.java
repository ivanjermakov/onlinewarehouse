package by.itechart.writeoffact.repository;

import by.itechart.writeoffact.entity.WriteOffAct;
import by.itechart.writeoffact.enums.WriteOffActType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface WriteOffActRepository extends JpaRepository<WriteOffAct, Long>, QuerydslPredicateExecutor<WriteOffAct> {

}
