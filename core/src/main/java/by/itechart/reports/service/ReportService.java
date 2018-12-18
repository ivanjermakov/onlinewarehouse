package by.itechart.reports.service;

import by.itechart.reports.dto.ReportDateFilter;

import java.io.IOException;
import java.io.InputStream;

public interface ReportService {

    InputStream getIncomeGoods(Long companyId, ReportDateFilter filter) throws IOException;

    InputStream getPersonalLoss(Long companyId, ReportDateFilter filter) throws IOException;

    InputStream getPaymentStatistics(Long companyId, ReportDateFilter filter) throws IOException;

    InputStream getWriteOffStatistics(Long companyId, ReportDateFilter filter) throws IOException;

    InputStream getClientsStatistics(ReportDateFilter filter) throws IOException;
}
