package by.itechart.writeoffact.service;

import by.itechart.commoditylot.dto.CreateCommodityLotDto;
import by.itechart.common.dto.Pair;
import by.itechart.writeoffact.dto.CreateWriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActFilter;
import by.itechart.writeoffact.dto.WriteOffActListDto;
import by.itechart.writeoffact.entity.WriteOffAct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface WriteOffActService {

    Page<WriteOffActListDto> getWriteOffActs(Long companyId, Pageable pageable, WriteOffActFilter writeOffActFilter);

    @Transactional(readOnly = true)
    List<WriteOffAct> findDamages(LocalDate start, LocalDate end);

    Long saveWriteOffAct(CreateWriteOffActDto writeOffAct, Long companyId);

    WriteOffActDto getWriteOffAct(Long companyId, Long writeOffActId);

    Pair<Long, Long> saveWriteOffActAndCommodityLot(Pair<CreateWriteOffActDto, CreateCommodityLotDto> writeOffActAndCommodityLot, Long companyId);

}
