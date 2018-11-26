package by.itechart.company.service;

import by.itechart.company.dto.CompanyDto;
import by.itechart.company.dto.CreateCompanyDto;
import by.itechart.company.entity.Company;
import by.itechart.company.enums.ActionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {

    Page<CompanyDto> getCompanies(Pageable pageable);

    Long saveCompany(CreateCompanyDto createCompanyDto);

    void newCompanyAction(Long companyId, ActionType actionType);
}
