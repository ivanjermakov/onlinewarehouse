package by.itechart.counterparty.service;

import by.itechart.common.repository.PieChartData;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.counterparty.dto.CounterpartyFilter;
import by.itechart.counterparty.dto.CreateCounterpartyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface CounterpartyService {

    Page<CounterpartyDto> getCounterparties(Long companyId, CounterpartyFilter filter, Pageable pageable);

    Long saveOrUpdateCounterparty(@Valid CreateCounterpartyDto counterparty);

    CounterpartyDto getCounterparty(Long companyId, Long counterpartyId);

    void deleteCounterparty(Long counterpartyId);

    List<PieChartData> getCounterpartiesGoodsCostStatistics(ConsignmentNoteType consignmentNoteType, long companyId);
}
