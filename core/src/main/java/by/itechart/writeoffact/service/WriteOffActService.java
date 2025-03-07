package by.itechart.writeoffact.service;

import by.itechart.commoditylot.dto.CreateCommodityLotDto;
import by.itechart.common.dto.Pair;
import by.itechart.common.repository.PieChartData;
import by.itechart.reports.dto.ReportDateFilter;
import by.itechart.writeoffact.dto.CreateWriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActFilter;
import by.itechart.writeoffact.dto.WriteOffActListDto;
import by.itechart.writeoffact.entity.WriteOffAct;
import by.itechart.writeoffact.repository.PersonalLossStatistics;
import by.itechart.writeoffact.repository.WriteOffStatistics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Validated
public interface WriteOffActService {

    Page<WriteOffActListDto> getWriteOffActs(Long companyId, Pageable pageable, WriteOffActFilter writeOffActFilter);

    Long saveWriteOffAct(@Valid CreateWriteOffActDto writeOffAct, Long companyId);

    @Transactional(readOnly = true) //TODO
    List<WriteOffAct> findDamages(LocalDate start, LocalDate end);

    WriteOffActDto getWriteOffAct(Long companyId, Long writeOffActId);

    Pair<Long, Long> saveWriteOffActAndCommodityLot(Pair<CreateWriteOffActDto, CreateCommodityLotDto> writeOffActAndCommodityLot, Long companyId);

    List<PersonalLossStatistics> getPersonalLossStatistics(Long companyId, ReportDateFilter filter);

    List<WriteOffStatistics> getWriteOffStatistics(Long companyId, ReportDateFilter filter);

    List<PieChartData> getActCreatorsStatistics(Long companyId);

}
