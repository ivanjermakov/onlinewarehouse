package by.itechart.profit.repository;

import by.itechart.profit.PaymentAct;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends PagingAndSortingRepository<PaymentAct, Long> {
    List<PaymentAct> getAllByPaymentDateBetween(LocalDateTime start, LocalDateTime end);

}
