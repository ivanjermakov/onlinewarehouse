package by.itechart.profit.service;

import by.itechart.profit.PaymentAct;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {

    List<PaymentAct> getPaymentForPeriod(LocalDateTime start, LocalDateTime end);

    PaymentAct persist(PaymentAct paymentAct);

    List<PaymentAct> makePayments();

    Long getDamages(LocalDate start, LocalDate end);

    void delete(Long id);
}
