package by.itechart.counterparty.service;

import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.counterparty.dto.CounterpartyFilter;
import by.itechart.counterparty.dto.CreateCounterpartyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CounterpartyService {

    Page<CounterpartyDto> getCounterparties(Long companyId, CounterpartyFilter filter, Pageable pageable);

    Long saveOrUpdateCounterparty(CreateCounterpartyDto counterparty);

    CounterpartyDto getCounterparty(Long counterpartyId);

    void deleteCounterparty(Long counterpartyId);
}
