package by.itechart.consignmentnote.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.dto.*;
import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.entity.ConsignmentNoteGoods;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.repository.ConsignmentNoteGoodsRepository;
import by.itechart.consignmentnote.repository.ConsignmentNoteRepository;
import by.itechart.exception.NotFoundEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsignmentNoteServiceImpl implements ConsignmentNoteService {

    private ConsignmentNoteRepository consignmentNoteRepository;
    private ConsignmentNoteGoodsRepository consignmentNoteGoodsRepository;

    public ConsignmentNoteServiceImpl(ConsignmentNoteRepository consignmentNoteRepository,
                                      ConsignmentNoteGoodsRepository consignmentNoteGoodsRepository) {
        this.consignmentNoteRepository = consignmentNoteRepository;
        this.consignmentNoteGoodsRepository = consignmentNoteGoodsRepository;
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

        return id;
    }

    @Override
    @Transactional
    public Long setConsignmentNoteStatus(long consignmentNoteId, ConsignmentNoteStatus consignmentNoteStatus, long companyId) {
        consignmentNoteRepository.setConsignmentNoteStatus(companyId, consignmentNoteId, consignmentNoteStatus);
        return consignmentNoteId;
    }

    @Override
    @Transactional
    public Long updateConsignmentNote(UpdateConsignmentNoteDto consignmentNoteDto, long companyId) {
        System.out.println(consignmentNoteDto);
        ConsignmentNote consignmentNote = consignmentNoteRepository.getOne(consignmentNoteDto.getId());
        ObjectMapperUtils.map(consignmentNoteDto, consignmentNote);

        return consignmentNote.getId();
//        ConsignmentNote consignmentNote = ObjectMapperUtils.map(consignmentNoteDto, ConsignmentNote.class);
//        Long id = consignmentNoteRepository.save(consignmentNote).getId();
//        List<ConsignmentNoteGoods> consignmentNoteGoodsList = consignmentNoteDto.getConsignmentNoteGoodsList()
//                .stream().map(dto -> {
//                    ConsignmentNoteGoods consignmentNoteGoods = ObjectMapperUtils.map(dto, ConsignmentNoteGoods.class);
//                    consignmentNoteGoods.setConsignmentNote(new ConsignmentNote(id));
//                    return consignmentNoteGoods;
//                }).collect(Collectors.toList());
//        consignmentNoteGoodsRepository.saveAll(consignmentNoteGoodsList);
//        return id;
    }
}
