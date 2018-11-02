package by.itechart.company.service;

import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.dto.CompanyDto;
import by.itechart.company.dto.CreateCompanyDto;
import by.itechart.company.entity.Company;
import by.itechart.company.entity.CompanyAction;
import by.itechart.company.enums.ActionType;
import by.itechart.company.repository.CompanyActionRepository;
import by.itechart.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<CompanyDto> getCompanies(Pageable pageable) {
        Page<Company> all = companyRepository.findAll(pageable);
        List<CompanyDto> companyDtoList = all.getContent().stream().filter(company -> !company.getId().equals(1L)).map(company -> {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setName(company.getName());
            companyDto.setSizeType(company.getSizeType());
            CompanyAction lastCompanyAction = company.getCompanyActions().get(company.getCompanyActions().size() - 1); // may work not correctly, because sequence may be incorrect(or not?)
            companyDto.setActionType(lastCompanyAction.getActionType());
            companyDto.setChange(lastCompanyAction.getChange());
            return companyDto;
        }).collect(Collectors.toList());
        return new PageImpl<CompanyDto>(companyDtoList, pageable, all.getTotalElements());
    }

    @Override
    @Transactional
    public Long saveCompany(CreateCompanyDto createCompanyDto) {
        Company company = ObjectMapperUtils.map(createCompanyDto, Company.class);
        //TODO: here should create new User with role COMPANY_ADMIN
        Long companyId = companyRepository.save(company).getId();
        newCompanyAction(companyId, ActionType.ENABLED);
        return companyId;
    }

    @Override
    @Transactional
    public void newCompanyAction(Long companyId, ActionType actionType) {
        CompanyAction companyAction = new CompanyAction();
        companyAction.setCompany(new Company(companyId));
        companyAction.setActionType(actionType);
        companyAction.setChange(LocalDateTime.now());
        companyActionRepository.save(companyAction);
    }
}
