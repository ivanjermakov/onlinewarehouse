package by.itechart.profit.service;

import by.itechart.company.enums.CompanySize;
import by.itechart.profit.Rate;

import java.time.LocalDateTime;

public interface RateService {

    Rate getLastRate(LocalDateTime end, CompanySize companySize);

    Rate persist(Rate rate);

}
