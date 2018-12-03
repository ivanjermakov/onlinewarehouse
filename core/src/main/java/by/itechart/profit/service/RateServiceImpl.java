package by.itechart.profit.service;

import by.itechart.company.enums.CompanySize;
import by.itechart.profit.Rate;
import by.itechart.profit.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class RateServiceImpl implements RateService{

    private final RateRepository rateRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public Rate getLastRate(LocalDateTime end, CompanySize companySize) {
        return rateRepository.getLastRate(end, companySize);
    }

    @Override
    public Rate persist(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public void delete(Long id) {
        rateRepository.setDeleted(id);
    }
}
