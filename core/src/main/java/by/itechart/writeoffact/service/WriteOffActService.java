package by.itechart.writeoffact.service;

import by.itechart.writeoffact.dto.CreateWriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActListDto;
import by.itechart.writeoffact.dto.WriteOffActFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WriteOffActService {

    Page<WriteOffActListDto> getWriteOffActs(Long companyId, Pageable pageable, WriteOffActFilter writeOffActFilter);

    Long saveWriteOffAct(CreateWriteOffActDto writeOffAct, Long companyId);

    WriteOffActListDto getWriteOffAct(Long writeOffActId);
}
