package by.itechart.profit.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.profit.PaymentAct;
import by.itechart.profit.dto.PaymentActDto;
import by.itechart.profit.repository.PaymentRepository;
import by.itechart.profit.repository.PaymentStatistics;
import by.itechart.reports.dto.ReportDateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    @Override
    public void savePaymentActList(List<PaymentActDto> paymentActDtoList) {
        List<PaymentAct> paymentActs = ObjectMapperUtils.mapAll(paymentActDtoList, PaymentAct.class);
        paymentRepository.saveAll(paymentActs);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PaymentStatistics> getPaymentStatistics(ReportDateFilter filter, Long companyId) {
        return paymentRepository.getPaymentStatistics(companyId, filter.getFrom(), filter.getTo());
    }
}
