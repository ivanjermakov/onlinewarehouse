package by.itechart.counterparty.service;

import by.itechart.common.entity.Address;
import by.itechart.common.repository.PieChartData;
import by.itechart.common.service.AddressService;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.counterparty.dto.CounterpartyFilter;
import by.itechart.counterparty.dto.CreateCounterpartyDto;
import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.repository.CounterpartyRepository;
import by.itechart.exception.NotFoundEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CounterpartyServiceImpl implements CounterpartyService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CounterpartyServiceImpl.class);

    private final CounterpartyRepository counterpartyRepository;
    private final AddressService addressService;

    public CounterpartyServiceImpl(CounterpartyRepository counterpartyRepository, AddressService addressService) {
        this.counterpartyRepository = counterpartyRepository;
        this.addressService = addressService;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CounterpartyDto> getCounterparties(Long companyId, CounterpartyFilter filter, Pageable pageable) {
        return counterpartyRepository.findAll(CounterpartyPredicates.findFilter(filter, companyId), pageable)
                .map(counterparty -> ObjectMapperUtils.map(counterparty, CounterpartyDto.class));
    }

    @Transactional
    @Override
    public Long saveOrUpdateCounterparty(CreateCounterpartyDto counterpartyDto) {
        Long addressId = addressService.saveAddress(counterpartyDto.getAddress());

        Counterparty counterparty = ObjectMapperUtils.map(counterpartyDto, Counterparty.class);
        counterparty.setId(null);
        counterparty.setAddress(new Address(addressId));
        Long id = counterpartyRepository.save(counterparty).getId();

        LOGGER.info("Carrier was created/updated with id: {}", id);

        return id;
    }

    @Transactional(readOnly = true)
    @Override
    public CounterpartyDto getCounterparty(Long companyId, Long counterpartyId) {
        Counterparty counterparty = counterpartyRepository.findByCompanyIdAndId(companyId, counterpartyId)
                .orElseThrow(() -> new NotFoundEntityException("Counterparty"));

        return ObjectMapperUtils.map(counterparty, CounterpartyDto.class);
    }

    @Transactional
    @Override
    public void deleteCounterparty(Long counterpartyId) {
        LOGGER.info("Delete counterparty with id: {}", counterpartyId);
        counterpartyRepository.setDeleted(counterpartyId);
    }

    @Override
    public List<PieChartData> getCounterpartiesGoodsCostStatistics(ConsignmentNoteType consignmentNoteType, long companyId) {
        return counterpartyRepository.getCounterpartiesGoodsCostStatistics(consignmentNoteType.toString(), companyId);
    }
}
