package by.itechart.counterparty.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.counterparty.dto.CounterpartyFilter;
import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.repository.CounterpartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;

    @Autowired
    public CounterpartyServiceImpl(CounterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }

    @Override
    public Page<CounterpartyDto> getCounterparties(Long companyId, CounterpartyFilter filter, Pageable pageable) {
        return counterpartyRepository.findAll(CounterpartyPredicates.findFilter(filter, companyId), pageable)
                .map(c -> ObjectMapperUtils.map(c, CounterpartyDto.class));
    }

    @Override
    public Long saveOrUpdateCounterparty(CounterpartyDto counterparty) {
        return counterpartyRepository.save(ObjectMapperUtils.map(counterparty, Counterparty.class)).getId();
    }

    @Override
    public CounterpartyDto getCounterparty(Long counterpartyId) {
        Optional<Counterparty> byId = counterpartyRepository.findById(counterpartyId);

        return byId.map(c -> ObjectMapperUtils.map(c, CounterpartyDto.class)).orElse(null);
    }

    @Override
    public void deleteCounterparty(Long counterpartyId) {
        counterpartyRepository.setDeleted(counterpartyId);
    }
}
