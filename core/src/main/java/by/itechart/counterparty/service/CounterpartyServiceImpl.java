package by.itechart.counterparty.service;

import by.itechart.common.entity.Address;
import by.itechart.common.service.AddressService;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.counterparty.dto.CounterpartyFilter;
import by.itechart.counterparty.dto.CreateCounterpartyDto;
import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.repository.CounterpartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;
    private final AddressService addressService;

    @Autowired
    public CounterpartyServiceImpl(CounterpartyRepository counterpartyRepository, AddressService addressService) {
        this.counterpartyRepository = counterpartyRepository;
        this.addressService = addressService;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CounterpartyDto> getCounterparties(Long companyId, CounterpartyFilter filter, Pageable pageable) {
        return counterpartyRepository.findAll(CounterpartyPredicates.findFilter(filter, companyId), pageable)
                .map(c -> ObjectMapperUtils.map(c, CounterpartyDto.class));
    }

    @Transactional
    @Override
    public Long saveOrUpdateCounterparty(CreateCounterpartyDto counterpartyDto) {
        Long addressId = addressService.saveAddress(counterpartyDto.getAddress());
        Counterparty map = ObjectMapperUtils.map(counterpartyDto, Counterparty.class);
        map.setId(null);
        map.setAddress(new Address(addressId));
        return counterpartyRepository.save(map).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public CounterpartyDto getCounterparty(Long counterpartyId) {
        Optional<Counterparty> byId = counterpartyRepository.findById(counterpartyId);

        return byId.map(c -> ObjectMapperUtils.map(c, CounterpartyDto.class)).orElse(null);
    }

    @Transactional
    @Override
    public void deleteCounterparty(Long counterpartyId) {
        counterpartyRepository.setDeleted(counterpartyId);
    }
}
