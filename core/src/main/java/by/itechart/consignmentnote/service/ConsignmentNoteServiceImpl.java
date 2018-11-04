package by.itechart.consignmentnote.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.consignmentnote.dto.ConsignmentNoteDto;
import by.itechart.consignmentnote.dto.CreateConsignmentNoteDto;
import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.consignmentnote.repository.ConsignmentNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ConsignmentNoteServiceImpl implements ConsignmentNoteService {

    private ConsignmentNoteRepository consignmentNoteRepository;

    @Autowired
    public ConsignmentNoteServiceImpl(ConsignmentNoteRepository consignmentNoteRepository) {
        this.consignmentNoteRepository = consignmentNoteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ConsignmentNoteDto> getConsignmentNotes(long companyId, ConsignmentNoteType consignmentNoteType,
                                                        LocalDate from, LocalDate to, Pageable pageable) {
        Page<ConsignmentNote> consignmentNotes = consignmentNoteRepository
                .findAll(ConsignmentNotePredicate.findFilter(companyId, consignmentNoteType, from, to), pageable);
        return consignmentNotes.map(consignmentNote -> ObjectMapperUtils.map(consignmentNote, ConsignmentNoteDto.class));
    }

    @Override
    @Transactional(readOnly = true)
    public ConsignmentNoteDto getConsignmentNote(long companyId, long consignmentNoteId) {
        ConsignmentNote consignmentNote = consignmentNoteRepository.findByCompanyIdAndId(companyId, consignmentNoteId);
        return ObjectMapperUtils.map(consignmentNote, ConsignmentNoteDto.class);
    }

    @Override
    @Transactional
    public ConsignmentNoteDto saveConsignmentNote(CreateConsignmentNoteDto createConsignmentNoteDto, long companyId) {
        ConsignmentNote consignmentNote = ObjectMapperUtils.map(createConsignmentNoteDto, ConsignmentNote.class);
        consignmentNote.getCompany().setId(companyId);
        // CHECK!!!

        return ObjectMapperUtils.map(consignmentNote, ConsignmentNoteDto.class);
    }
}
