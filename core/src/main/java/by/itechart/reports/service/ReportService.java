package by.itechart.reports.service;

import by.itechart.reports.dto.ReportDateFilter;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

public interface ReportService {

    InputStream getIncomeGoods(Long companyId, ReportDateFilter filter) throws IOException;

    InputStream getPersonalLoss(Long companyId, ReportDateFilter filter) throws IOException;

    void test(Long companyId, LocalDate from, LocalDate to) throws IOException;
}
