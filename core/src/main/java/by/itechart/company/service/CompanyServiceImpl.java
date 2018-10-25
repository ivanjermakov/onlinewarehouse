package by.itechart.company.service;

import by.itechart.company.entity.Company;
import by.itechart.company.enums.ActionType;
import by.itechart.company.repository.CompanyActionRepository;
import by.itechart.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private CompanyActionRepository companyActionRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository,
                              CompanyActionRepository companyActionRepository) {
        this.companyRepository = companyRepository;
        this.companyActionRepository = companyActionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Company> getCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Long saveOrUpdateUser(Company company) {
        return companyRepository.save(company).getId();
    }

    @Override
    @Transactional
    public void changeActionType(Long companyId, ActionType actionType) {
        companyActionRepository.changeActionType(companyId, actionType); // modify
    }
}
