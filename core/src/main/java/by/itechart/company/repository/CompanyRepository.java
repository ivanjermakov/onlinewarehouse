package by.itechart.company.repository;

import by.itechart.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CompanyRepository extends JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {

    Company getById(Long id);

    @Query("select logo from Company c where c.id = ?1")
    String getCompanyLogoById(Long id);
}
