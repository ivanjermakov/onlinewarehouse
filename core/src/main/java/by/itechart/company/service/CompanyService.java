package by.itechart.company.service;

import by.itechart.company.entity.Company;
import by.itechart.company.enums.ActionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {

    Page<Company> getCompanies(Pageable pageable);

    Long saveOrUpdateUser(Company company);

    void changeActionType(Long companyId, ActionType actionType);
}
