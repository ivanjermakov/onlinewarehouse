package by.itechart.counterparty.service;

import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.counterparty.dto.CounterpartyFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CounterpartyService {

    Page<CounterpartyDto> getCounterparties(Long companyId, CounterpartyFilter filter, Pageable pageable);

    Long saveOrUpdateCounterparty(CounterpartyDto counterparty);

    CounterpartyDto getCounterparty(Long counterpartyId);

    void deleteCounterparty(Long counterpartyId);
}
