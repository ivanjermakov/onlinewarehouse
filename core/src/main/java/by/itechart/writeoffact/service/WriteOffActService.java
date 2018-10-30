package by.itechart.writeoffact.service;

import by.itechart.writeoffact.dto.CreateWriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActDto;
import by.itechart.writeoffact.entity.WriteOffAct;
import by.itechart.writeoffact.enums.WriteOffActType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface WriteOffActService {

    Page<WriteOffAct> getWriteOffActs(Long companyId, Pageable pageable, WriteOffActType writeOffActType, LocalDate from, LocalDate to);

    Long saveWriteOffAct(CreateWriteOffActDto writeOffAct);

    WriteOffActDto getWriteOffAct(Long writeOffActId);
}
