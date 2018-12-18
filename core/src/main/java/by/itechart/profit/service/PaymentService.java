package by.itechart.profit.service;

import by.itechart.profit.dto.PaymentActDto;
import by.itechart.profit.repository.PaymentStatistics;
import by.itechart.reports.dto.ReportDateFilter;

import java.util.List;

public interface PaymentService {

    void savePaymentActList(List<PaymentActDto> paymentActDtoList);

    List<PaymentStatistics> getPaymentStatistics(ReportDateFilter filter, Long companyId);

}
