package by.itechart.company.repository;

import by.itechart.company.entity.CompanyAction;
import by.itechart.company.enums.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyActionRepository extends JpaRepository<CompanyAction, Long> {
    @Modifying
    @Query("update CompanyAction c set c.action_type = :actionType where c.company_id = :companyId")
    void changeActionType(@Param("companyId") Long companyId, @Param("actionType") ActionType actionType);
}