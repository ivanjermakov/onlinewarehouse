package by.itechart.company.repository;

import by.itechart.company.entity.CompanyAction;
import by.itechart.company.enums.ActionType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyActionRepository extends CrudRepository<CompanyAction, Long> {
}