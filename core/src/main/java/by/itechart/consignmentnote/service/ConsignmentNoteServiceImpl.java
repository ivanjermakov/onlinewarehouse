package by.itechart.consignmentnote.service;

import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.consignmentnote.repository.ConsignmentNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsignmentNoteServiceImpl implements ConsignmentNoteService {
    private ConsignmentNoteRepository consignmentNoteRepository;

    @Autowired
    public ConsignmentNoteServiceImpl(ConsignmentNoteRepository consignmentNoteRepository) {
        this.consignmentNoteRepository = consignmentNoteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ConsignmentNote> getConsignmentNotes(ConsignmentNoteType consignmentNoteType, Pageable pageable) {
        return consignmentNoteRepository.findAllByConsignmentNoteType(consignmentNoteType, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ConsignmentNote getConsignmentNote(long consignmentNoteId) {
        return consignmentNoteRepository.findById(consignmentNoteId).orElse(null);
    }

    @Override
    @Transactional
    public Long saveOrUpdateConsignmentNote(ConsignmentNote consignmentNote) {
        return consignmentNoteRepository.save(consignmentNote).getId();
    }
}
