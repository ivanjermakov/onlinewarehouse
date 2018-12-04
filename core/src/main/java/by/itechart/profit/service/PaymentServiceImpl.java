package by.itechart.profit.service;

import by.itechart.commoditylot.entity.CommodityLot;
import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.commoditylot.service.CommodityLotService;
import by.itechart.company.enums.CompanySize;
import by.itechart.profit.PaymentAct;
import by.itechart.profit.Rate;
import by.itechart.profit.repository.PaymentRepository;
import by.itechart.profit.repository.RateRepository;
import by.itechart.writeoffact.entity.WriteOffAct;
import by.itechart.writeoffact.service.WriteOffActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RateRepository rateRepository;
    private final CommodityLotService commodityLotService;
    private final WriteOffActService writeOffActService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              RateRepository rateRepository,
                              CommodityLotService commodityLotService,
                              WriteOffActService writeOffActService) {
        this.paymentRepository = paymentRepository;
        this.rateRepository = rateRepository;
        this.commodityLotService = commodityLotService;
        this.writeOffActService = writeOffActService;
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
    public Long getDamages(LocalDate start, LocalDate end) {
        List<WriteOffAct> writeOffActs = writeOffActService.findDamages(start, end);
        Map<CompanySize, Rate> rates = new EnumMap<>(CompanySize.class);

        LocalDateTime findDate = end.plusDays(1).atStartOfDay();

        rates.put(CompanySize.SMALL, rateRepository.getLastRate(findDate, CompanySize.SMALL));
        rates.put(CompanySize.MEDIUM, rateRepository.getLastRate(findDate, CompanySize.MEDIUM));
        rates.put(CompanySize.LARGE, rateRepository.getLastRate(findDate, CompanySize.LARGE));

         return writeOffActs.stream().map(act -> {
           return rates.get(act.getCompany().getSizeType()).getRate();
        }).mapToLong(i -> i).sum();
    }
}
