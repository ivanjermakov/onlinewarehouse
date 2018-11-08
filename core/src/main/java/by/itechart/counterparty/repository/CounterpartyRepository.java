package by.itechart.counterparty.repository;

import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.enums.CounterpartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CounterpartyRepository extends JpaRepository<Counterparty, Long>, QuerydslPredicateExecutor<Counterparty> {

    Page<Counterparty> findAllByCompany_IdAndCounterpartyType(Long companyId, CounterpartyType counterpartyType, Pageable pageable);

    @Modifying
    @Query("update Counterparty c set c.deleted = current_date where c.id = ?1")
    void setDeleted(Long counterpartyId);
}
