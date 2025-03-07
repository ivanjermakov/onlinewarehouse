package by.itechart.company.service;

import by.itechart.common.dto.AuthorityDto;
import by.itechart.common.entity.AuthorityName;
import by.itechart.common.service.UserService;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.dto.CompanyDto;
import by.itechart.company.dto.CreateCompanyDto;
import by.itechart.company.entity.Company;
import by.itechart.company.entity.CompanyAction;
import by.itechart.company.enums.ActionType;
import by.itechart.company.repository.CompanyActionRepository;
import by.itechart.company.repository.CompanyElasticRepository;
import by.itechart.company.repository.CompanyRepository;
import by.itechart.reports.dto.ReportDateFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
    private final CompanyElasticRepository companyElasticRepository;
    private final UserService userService;
    private CompanyRepository companyRepository;
    private CompanyActionRepository companyActionRepository;
    private final LocalDateTime MAX_LOCAL_DATE_TIME = LocalDateTime.of(2100, 1, 1, 0, 0);

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              CompanyActionRepository companyActionRepository,
                              CompanyElasticRepository companyElasticRepository,
                              UserService userService) {
        this.companyRepository = companyRepository;
        this.companyActionRepository = companyActionRepository;
        this.companyElasticRepository = companyElasticRepository;
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDto> getCompanies(Pageable pageable) {
        Page<Company> all = companyRepository.findAll(CompanyPredicates.findExcludeFirstCompany(), pageable);

        List<CompanyDto> companyDtoList = all.getContent().stream().map(company -> {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setName(company.getName());
            companyDto.setSizeType(company.getSizeType());
//            may work not correctly, because sequence may be incorrect(or not?)
//            Tested: doesn't work and cause ArrayIndexOutOfBoundsException
//            TODO: fix
//            CompanyAction lastCompanyAction = company.getCompanyActions().get(company.getCompanyActions().size() - 1);
            List<CompanyAction> sorted = company.getCompanyActions().stream().sorted(Comparator.comparing(CompanyAction::getStart)).collect(Collectors.toList());
            CompanyAction lastCompanyAction = sorted.get(sorted.size() - 1);
            if (!lastCompanyAction.getEnd().equals(MAX_LOCAL_DATE_TIME)) {
                companyDto.setActionType(ActionType.DISABLED);
                companyDto.setChange(lastCompanyAction.getEnd());
            } else {
                companyDto.setActionType(ActionType.ENABLED);
                companyDto.setChange(lastCompanyAction.getStart());
            }
            return companyDto;
        }).collect(Collectors.toList());

        return new PageImpl<CompanyDto>(companyDtoList, pageable, all.getTotalElements());
//        return companies.map(company -> ObjectMapperUtils.map(company, CompanyDto.class));
    }

    @Override
    @Transactional
    public Long saveCompany(CreateCompanyDto createCompanyDto) {
        Company company = ObjectMapperUtils.map(createCompanyDto, Company.class);
        Long companyId = companyRepository.save(company).getId();

        createCompanyDto.getCreateUserDto().getAuthorities().clear();
        createCompanyDto.getCreateUserDto().getAuthorities().add(new AuthorityDto(AuthorityName.ROLE_COMPANY_ADMIN));
        userService.saveUser(companyId, createCompanyDto.getCreateUserDto());

        newCompanyAction(companyId, ActionType.ENABLED);
        companyElasticRepository.save(company);

        LOGGER.info("Company was created with id: {}", companyId);
        return companyId;
    }

    @Override
    @Transactional
    public void newCompanyAction(Long companyId, ActionType actionType) {
        CompanyAction companyAction;
        if (actionType == ActionType.ENABLED) {
            companyAction = new CompanyAction();
            companyAction.setCompany(new Company(companyId));
            companyAction.setStart(LocalDateTime.now());
            companyAction.setEnd(MAX_LOCAL_DATE_TIME);
        } else {
            List<CompanyAction> allByCompanyId = companyActionRepository.findAllByCompany_Id(companyId);
            List<CompanyAction> sorted = allByCompanyId
                    .stream()
                    .sorted(Comparator.comparing(CompanyAction::getStart))
                    .collect(Collectors.toList());
            companyAction = sorted.get(sorted.size() - 1);
            companyAction.setEnd(LocalDateTime.now());
        }
        companyActionRepository.save(companyAction);

        LOGGER.info("CommodityLot action type change to: {}", actionType);
    }

    @Override
    @Transactional
    public Page<Company> findAllByName(String name, Pageable pageable) {
        LOGGER.info("Find companies by name: {}", name);
        return companyElasticRepository.findAllByName(name, pageable);
    }

    @Override
    public String getCompanyLogoById(Long id) {
        return companyRepository.getCompanyLogoById(id);
    }

    @Override
    public List<CompanyAction> getClientsStatistics(ReportDateFilter filter) {
        return companyActionRepository.getCompanyActionStatistics(filter.getFrom().atStartOfDay(), filter.getTo().atTime(23, 59, 59, 999));
    }
}
