package by.itechart.profit.service;

import by.itechart.profit.PaymentAct;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {

    List<PaymentAct> getPaymentForPeriod(LocalDateTime start, LocalDateTime end);

    PaymentAct persist(PaymentAct paymentAct);

    List<PaymentAct> makePayments();

    void delete(Long id);
}
