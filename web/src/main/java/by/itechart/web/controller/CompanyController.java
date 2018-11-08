package by.itechart.web.controller;

import by.itechart.company.dto.CompanyDto;
import by.itechart.company.dto.CreateCompanyDto;
import by.itechart.company.enums.ActionType;
import by.itechart.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public Page<CompanyDto> getCompanies(Pageable pageable) {
        return companyService.getCompanies(pageable);
    }

    @PostMapping
    public Long saveCompany(@RequestBody CreateCompanyDto createCompanyDto) {
        return companyService.saveCompany(createCompanyDto);
    }

    @PatchMapping("/{id}/disable")
    @ResponseStatus(value = HttpStatus.OK)
    public void disableCompany(@PathVariable long id) {
        companyService.newCompanyAction(id, ActionType.DISABLED);
    }

    @PatchMapping("/{id}/enable")
    @ResponseStatus(value = HttpStatus.OK)
    public void enableCompany(@PathVariable long id) {
        companyService.newCompanyAction(id, ActionType.ENABLED);
    }
}
