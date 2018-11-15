package by.itechart.consignmentnote.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.dto.ConsignmentNoteDto;
import by.itechart.consignmentnote.dto.ConsignmentNoteFilter;
import by.itechart.consignmentnote.dto.ConsignmentNoteListDto;
import by.itechart.consignmentnote.dto.CreateConsignmentNoteDto;
import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.entity.ConsignmentNoteGoods;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.repository.ConsignmentNoteGoodsRepository;
import by.itechart.consignmentnote.repository.ConsignmentNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public ConsignmentNoteServiceImpl(ConsignmentNoteRepository consignmentNoteRepository,
                                      ConsignmentNoteGoodsRepository consignmentNoteGoodsRepository) {
        this.consignmentNoteRepository = consignmentNoteRepository;
        this.consignmentNoteGoodsRepository = consignmentNoteGoodsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ConsignmentNoteListDto> getConsignmentNotes(long companyId, ConsignmentNoteFilter consignmentNoteFilter, Pageable pageable) {
        Page<ConsignmentNote> consignmentNotes = consignmentNoteRepository
                .findAll(ConsignmentNotePredicate.findFilter(companyId, consignmentNoteFilter), pageable);
        return consignmentNotes.map(consignmentNote -> ObjectMapperUtils.map(consignmentNote, ConsignmentNoteListDto.class));
    }

    @Override
    @Transactional(readOnly = true)
    public ConsignmentNoteDto getConsignmentNote(long companyId, long consignmentNoteId) {
        ConsignmentNote consignmentNote = consignmentNoteRepository.findByCompanyIdAndId(companyId, consignmentNoteId);
        return ObjectMapperUtils.map(consignmentNote, ConsignmentNoteDto.class);
    }

    @Override
    @Transactional
    public Long saveConsignmentNote(CreateConsignmentNoteDto createConsignmentNoteDto, long companyId) {
        System.out.println(createConsignmentNoteDto.getCreatorId());
        ConsignmentNote consignmentNote = ObjectMapperUtils.map(createConsignmentNoteDto, ConsignmentNote.class);
        consignmentNote.setCompany(new Company(companyId));
        consignmentNote.setId(null);
        consignmentNote.setRegistration(LocalDate.now());
        consignmentNote.setConsignmentNoteStatus(ConsignmentNoteStatus.NOT_PROCESSED);
        // TODO driverId? creatorId
        Long id = consignmentNoteRepository.save(consignmentNote).getId();
        List<ConsignmentNoteGoods> consignmentNoteGoodsList = createConsignmentNoteDto.getConsignmentNoteGoodsList()
                .stream().map(dto -> {
                    ConsignmentNoteGoods consignmentNoteGoods = ObjectMapperUtils.map(dto, ConsignmentNoteGoods.class);
//                    consignmentNoteGoods.setId(null);
                    consignmentNoteGoods.setConsignmentNote(new ConsignmentNote(id));
                    return consignmentNoteGoods;
                }).collect(Collectors.toList());
        consignmentNoteGoodsRepository.saveAll(consignmentNoteGoodsList);
        return id;
    }
}
