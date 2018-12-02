package by.itechart.consignmentnote.service;

import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.entity.Driver;
import by.itechart.carrier.repository.CarrierRepository;
import by.itechart.carrier.repository.DriverRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.dto.*;
import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.entity.ConsignmentNoteGoods;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.repository.ConsignmentNoteGoodsRepository;
import by.itechart.consignmentnote.repository.ConsignmentNoteRepository;
import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.repository.CounterpartyRepository;
import by.itechart.exception.NotFoundEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsignmentNoteServiceImpl implements ConsignmentNoteService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConsignmentNoteServiceImpl.class);

    private ConsignmentNoteRepository consignmentNoteRepository;
    private ConsignmentNoteGoodsRepository consignmentNoteGoodsRepository;
    private CounterpartyRepository counterpartyRepository;
    private CarrierRepository carrierRepository;
    private DriverRepository driverRepository;

    public ConsignmentNoteServiceImpl(ConsignmentNoteRepository consignmentNoteRepository,
                                      ConsignmentNoteGoodsRepository consignmentNoteGoodsRepository,
                                      CarrierRepository carrierRepository,
                                      CounterpartyRepository counterpartyRepository,
                                      DriverRepository driverRepository) {
        this.consignmentNoteRepository = consignmentNoteRepository;
        this.consignmentNoteGoodsRepository = consignmentNoteGoodsRepository;
        this.counterpartyRepository = counterpartyRepository;
        this.carrierRepository = carrierRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ConsignmentNoteListDto> getConsignmentNotes(long companyId, ConsignmentNoteFilter consignmentNoteFilter, Pageable pageable) {
        return consignmentNoteRepository
                .findAll(ConsignmentNotePredicate.findFilter(companyId, consignmentNoteFilter), pageable)
                .map(consignmentNote -> ObjectMapperUtils.map(consignmentNote, ConsignmentNoteListDto.class));
    }

    @Override
    @Transactional(readOnly = true)
    public ConsignmentNoteDto getConsignmentNote(long companyId, long consignmentNoteId) {
        ConsignmentNote consignmentNote = consignmentNoteRepository.findByCompanyIdAndId(companyId, consignmentNoteId)
                .orElseThrow(() -> new NotFoundEntityException("ConsignmentNote"));

        return ObjectMapperUtils.map(consignmentNote, ConsignmentNoteDto.class);
    }

    @Override
    @Transactional
    public Long saveConsignmentNote(CreateConsignmentNoteDto createConsignmentNoteDto, long companyId) {
        System.out.println(createConsignmentNoteDto);
        ConsignmentNote consignmentNote = ObjectMapperUtils.map(createConsignmentNoteDto, ConsignmentNote.class);
        consignmentNote.setCompany(new Company(companyId));
        consignmentNote.setId(null);
        consignmentNote.setRegistration(LocalDate.now());
        consignmentNote.setConsignmentNoteStatus(ConsignmentNoteStatus.NOT_PROCESSED);

        Long id = consignmentNoteRepository.save(consignmentNote).getId();

        List<ConsignmentNoteGoods> consignmentNoteGoodsList = createConsignmentNoteDto.getConsignmentNoteGoodsList()
                .stream().map(dto -> {
                    ConsignmentNoteGoods consignmentNoteGoods = ObjectMapperUtils.map(dto, ConsignmentNoteGoods.class);
                    consignmentNoteGoods.setConsignmentNote(new ConsignmentNote(id));
                    return consignmentNoteGoods;
                }).collect(Collectors.toList());
        consignmentNoteGoodsRepository.saveAll(consignmentNoteGoodsList);

        LOGGER.info("Consignment note was created with id: {}", id);

        return id;
    }

    @Override
    @Transactional
    public Long setConsignmentNoteStatus(long consignmentNoteId, ConsignmentNoteStatus consignmentNoteStatus, long companyId) {
        LOGGER.info("Consignment note status change to: {}", consignmentNoteStatus);
        consignmentNoteRepository.setConsignmentNoteStatus(companyId, consignmentNoteId, consignmentNoteStatus);
        return consignmentNoteId;
    }

    @Override
    @Transactional
    public Long updateConsignmentNote(UpdateConsignmentNoteDto consignmentNoteDto, long companyId) {
        System.out.println(consignmentNoteDto.getConsignmentNoteGoodsList());

        ConsignmentNote consignmentNote = consignmentNoteRepository.findById(consignmentNoteDto.getId())
                .orElseThrow(() -> new NotFoundEntityException("ConsignmentNote"));
        ObjectMapperUtils.map(consignmentNoteDto, consignmentNote);

        Counterparty counterparty = counterpartyRepository.getOne(consignmentNoteDto.getCounterpartyId());
        Carrier carrier = carrierRepository.getOne(consignmentNoteDto.getCarrierId());
        Driver driver = driverRepository.getOne(consignmentNoteDto.getDriverId());

//
//        List<ConsignmentNoteGoods> consignmentNoteGoodsList = consignmentNoteDto.getConsignmentNoteGoodsList()
//                .stream().map(dto -> {
//                    ConsignmentNoteGoods consignmentNoteGoods = ObjectMapperUtils.map(dto, ConsignmentNoteGoods.class);
//                    consignmentNoteGoods.setConsignmentNote(new ConsignmentNote(consignmentNoteDto.getId()));
//                    return consignmentNoteGoodsRepository.getOne(consignmentNoteGoods.getId());
//                }).collect(Collectors.toList());
//
//        System.out.println(consignmentNoteGoodsList);

        consignmentNote.setCounterparty(counterparty);
        consignmentNote.setCarrier(carrier);
        consignmentNote.setDriver(driver);
//        consignmentNote.setConsignmentNoteGoodsList(consignmentNoteGoodsList);

        LOGGER.info("Edit consignment note with id: {}", consignmentNote.getId());

        return consignmentNote.getId();
    }
}
