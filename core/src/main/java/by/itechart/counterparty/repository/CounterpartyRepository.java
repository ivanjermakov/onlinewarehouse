package by.itechart.counterparty.repository;

import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.enums.CounterpartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CounterpartyRepository extends CrudRepository<Counterparty, Long> {

    Page<Counterparty> findAllByCompany_IdAndCounterpartyType(Long companyId, CounterpartyType counterpartyType, Pageable pageable);

    @Modifying
    @Query("update Counterparty c set c.deleted = current_date where c.id = ?1")
    void setDeleted(Long counterpartyId);
}
