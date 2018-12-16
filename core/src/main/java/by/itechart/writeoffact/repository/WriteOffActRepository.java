package by.itechart.writeoffact.repository;

import by.itechart.common.repository.PieChartData;
import by.itechart.writeoffact.entity.WriteOffAct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WriteOffActRepository extends JpaRepository<WriteOffAct, Long>, QuerydslPredicateExecutor<WriteOffAct> {

    Optional<WriteOffAct> findByCompanyIdAndId(long companyId, long writeOffActId);

    @Query(value = "select woa.responsible_person as responsiblePerson, SUM(woa.total_amount) as total " +
            "from write_off_act woa " +
            "where woa.company_id = :companyId " +
            "  and woa.write_off_act_type = 'WAREHOUSE' " +
            "  and woa.creation between :dateFrom and :dateTo " +
            "group by woa.responsible_person " +
            "order by responsible_person asc;", nativeQuery = true)
    List<PersonalLossStatistics> getPersonalLossStatistics(@Param("companyId") Long companyId,
                                                           @Param("dateFrom") LocalDate from,
                                                           @Param("dateTo") LocalDate to);

    @Query(value = "select concat(u.first_name, ' ', u.last_name, ' ', u.patronymic) as name, count(woa.id) as y " +
            "from write_off_act woa " +
            "       inner join users u on woa.creator_id = u.id " +
            "where woa.company_id = :companyId " +
            "group by u.first_name, u.last_name, u.patronymic", nativeQuery = true)
    List<PieChartData> getActCreatorsStatistics(@Param("companyId") Long companyId);

}
