package by.itechart.web.controller;

import by.itechart.company.entity.Company;
import by.itechart.company.enums.CompanySize;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController()
public class CompanyController {

    @GetMapping("/companies")
    public List<Company> getCompanies(@RequestParam(required = false) Pageable pageable){
        ArrayList<Company> companies = new ArrayList<>();
        // return list of companies
        for (int i = 0; i < 10; i++) {
            companies.add(createCompany(i));
        }
        return companies;
    }

    @PostMapping("/companies")
    public Long saveCompany(@RequestBody Company company){
        // return company id
        Long aLong = new Long(10);
        return aLong;
    }

    @PatchMapping("/companies/{id}/disable")
    @ResponseStatus(value = HttpStatus.OK)
    public void disableCompany(@PathVariable long id){
        // set CompanyAction disabled
    }

    @PatchMapping("/companies/{id}/enable")
    @ResponseStatus(value = HttpStatus.OK)
    public void enableCompany(@PathVariable long id) {
        // set CompanyAction enabled
    }

    private Company createCompany(long id){
        Company company = new Company();
        company.setName("company" + id);
        company.setSizeType(CompanySize.values()[new Random().nextInt(3)]);
        return company;
    }
}
