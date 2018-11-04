package by.itechart.writeoffact.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.writeoffact.dto.CreateWriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActDto;
import by.itechart.writeoffact.entity.WriteOffAct;
import by.itechart.writeoffact.enums.WriteOffActType;
import by.itechart.writeoffact.repository.WriteOffActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WriteOffActServiceImpl implements WriteOffActService {

    private final WriteOffActRepository writeOffActRepository;

    @Autowired
    public WriteOffActServiceImpl(WriteOffActRepository writeOffActRepository) {
        this.writeOffActRepository = writeOffActRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WriteOffAct> getWriteOffActs(Long companyId, Pageable pageable, WriteOffActType writeOffActType, LocalDate from, LocalDate to) {
        if (from == null && to == null) {
            return writeOffActRepository.findAllByCompany_IdAndWriteOffActType(companyId, writeOffActType, pageable);
        }
        if (from != null && to != null) {
            return writeOffActRepository.findAllByCompany_IdAndWriteOffActTypeAndCreationBetween(companyId, writeOffActType, from, to, pageable);
        } else {
            if (from != null) {
                return writeOffActRepository.findAllByCompany_IdAndWriteOffActTypeAndCreationAfter(companyId, writeOffActType, from, pageable);
            } else {
                return writeOffActRepository.findAllByCompany_IdAndWriteOffActTypeAndCreationBefore(companyId, writeOffActType, to, pageable);
            }
        }
    }

    @Transactional
    @Override
    public Long saveWriteOffAct(CreateWriteOffActDto createWriteOffActDto) {
        WriteOffAct writeOffAct = new WriteOffAct(null);
        writeOffAct.setCompany(new Company(createWriteOffActDto.getCompany()));
        writeOffAct.setCreation(createWriteOffActDto.getCreation());
        writeOffAct.setCreator(createWriteOffActDto.getCreator());
        writeOffAct.setResponsiblePerson(createWriteOffActDto.getResponsiblePerson());
        writeOffAct.setTotalAmount(createWriteOffActDto.getTotalAmount());

        return writeOffActRepository.save(writeOffAct).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public WriteOffActDto getWriteOffAct(Long writeOffActId) {
        Optional<WriteOffAct> byId = writeOffActRepository.findById(writeOffActId);
        return byId.map(o -> ObjectMapperUtils.map(o, WriteOffActDto.class)).orElse(null);
    }
}
