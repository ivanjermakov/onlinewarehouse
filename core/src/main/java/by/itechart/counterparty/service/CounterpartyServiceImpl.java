package by.itechart.counterparty.service;

import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.enums.CounterpartyType;
import by.itechart.counterparty.repository.CounterpartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;

    @Autowired
    public CounterpartyServiceImpl(CounterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }

    @Override
    public Page<Counterparty> getCounterparties(Long companyId, CounterpartyType counterpartyType, Pageable pageable) {
        return counterpartyRepository.findAllByCompany_IdAndCounterpartyType(companyId, counterpartyType, pageable);
//        return null;
    }

    @Override
    public Long saveOrUpdateCounterparty(Counterparty counterparty) {
        return counterpartyRepository.save(counterparty).getId();
    }

    @Override
    public Counterparty getCounterparty(Long counterpartyId) {
        return counterpartyRepository.findById(counterpartyId).orElse(null);
    }

    @Override
    public void deleteCounterparty(Long counterpartyId) {
        counterpartyRepository.setDeleted(counterpartyId);
    }
}
