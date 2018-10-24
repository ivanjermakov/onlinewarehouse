package by.itechart.counterparty.service;

import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.enums.CounterpartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CounterpartyService {

    Page<Counterparty> getCounterparties(Long companyId, CounterpartyType counterpartyType, Pageable pageable);

    Long saveOrUpdateCounterparty(Counterparty counterparty);

    Counterparty getCounterparty(Long counterpartyId);

    void deleteCounterparty(Long counterpartyId);
}
