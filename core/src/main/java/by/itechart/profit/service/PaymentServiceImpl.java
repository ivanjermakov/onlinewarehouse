package by.itechart.profit.service;

import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.commoditylot.service.CommodityLotService;
import by.itechart.company.enums.CompanySize;
import by.itechart.profit.PaymentAct;
import by.itechart.profit.Rate;
import by.itechart.profit.repository.PaymentRepository;
import by.itechart.profit.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RateRepository rateRepository;
    private final CommodityLotService commodityLotService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, RateRepository rateRepository, CommodityLotService commodityLotService) {
        this.paymentRepository = paymentRepository;
        this.rateRepository = rateRepository;
        this.commodityLotService = commodityLotService;
    }

    @Override
    public List<PaymentAct> getPaymentForPeriod(LocalDateTime start, LocalDateTime end) {
        return paymentRepository.getAllByPaymentDateBetween(start, end);
    }

    @Override
    public PaymentAct persist(PaymentAct paymentAct) {
        return paymentRepository.save(paymentAct);
    }

    @Override
    public List<PaymentAct> makePayments() {
        List<CommodityLot> lots = commodityLotService.getAllByCommodityLotType(CommodityLotType.IN);
        Map<CompanySize, Rate> rates = new EnumMap<>(CompanySize.class);

        rates.put(CompanySize.SMALL, rateRepository.getLastRate(LocalDateTime.now(), CompanySize.SMALL));
        rates.put(CompanySize.MEDIUM, rateRepository.getLastRate(LocalDateTime.now(), CompanySize.MEDIUM));
        rates.put(CompanySize.LARGE, rateRepository.getLastRate(LocalDateTime.now(), CompanySize.LARGE));

        List<PaymentAct> paymentActList = new ArrayList<>();

        lots.stream().map(lot -> {
            PaymentAct paymentAct = new PaymentAct();
            paymentAct.setCompany(lot.getCompany());
            paymentAct.setRateExacted(rates.get(lot.getCompany().getSizeType()));
            paymentAct.setPaymentDate(LocalDateTime.now());
            return paymentRepository.save(paymentAct);
        }).forEach(paymentActList::add);

        return paymentActList;
    }

    @Override
    public void delete(Long id) {
        paymentRepository.setDeleted(id);
    }
}
