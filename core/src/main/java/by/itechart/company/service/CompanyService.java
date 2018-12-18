package by.itechart.company.service;

import by.itechart.company.dto.CompanyDto;
import by.itechart.company.dto.CreateCompanyDto;
import by.itechart.company.entity.Company;
import by.itechart.company.entity.CompanyAction;
import by.itechart.company.enums.ActionType;
import by.itechart.reports.dto.ReportDateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface CompanyService {

    Page<CompanyDto> getCompanies(Pageable pageable);

    Long saveCompany(@Valid CreateCompanyDto createCompanyDto);

    void newCompanyAction(Long companyId, ActionType actionType);

    Page<Company> findAllByName(String name, Pageable pageable);

    String getCompanyLogoById(Long id);

    List<CompanyAction> getClientsStatistics(ReportDateFilter filter);

}
