package by.itechart.counterparty.service;

import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.counterparty.enums.CounterpartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CounterpartyService {

    Page<CounterpartyDto> getCounterparties(Long companyId, CounterpartyType counterpartyType, Pageable pageable);

    Long saveOrUpdateCounterparty(CounterpartyDto counterparty);

    CounterpartyDto getCounterparty(Long counterpartyId);

    void deleteCounterparty(Long counterpartyId);
}
