package by.itechart.profit.repository;

import by.itechart.profit.PaymentAct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends PagingAndSortingRepository<PaymentAct, Long> {

//    List<PaymentAct> getAllByPaymentDateBetween(LocalDateTime start, LocalDateTime end);

    @Query(value = "select extract(year from pa.creation) as dateYear, extract(month from pa.creation) as dateMonth, " +
            "       sum(amount) as total " +
            "from payment_act pa " +
            "inner join placement p on pa.placement_id = p.id " +
            "inner join warehouse w2 on p.warehouse_id = w2.id " +
            "where w2.company_id = :companyId " +
            "and pa.creation between :dateFrom and :dateTo " +
            "group by extract(year from pa.creation), extract(month from pa.creation) " +
            "order by dateYear, dateMonth", nativeQuery = true)
    List<PaymentStatistics> getPaymentStatistics(@Param("companyId") Long companyId,
                                                 @Param("dateFrom") LocalDate from,
                                                 @Param("dateTo") LocalDate to);
}
