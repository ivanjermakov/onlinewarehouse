package by.itechart.web.controller;

import by.itechart.company.dto.CompanyDto;
import by.itechart.company.dto.CreateCompanyDto;
import by.itechart.company.enums.ActionType;
import by.itechart.company.service.CompanyService;
import by.itechart.reports.dto.ReportDateFilter;
import by.itechart.reports.service.ReportService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;
    private ReportService reportService;

    @Autowired
    public CompanyController(CompanyService companyService, ReportService reportService) {
        this.companyService = companyService;
        this.reportService = reportService;
    }

    @GetMapping
    public Page<CompanyDto> getCompanies(Pageable pageable) {
        return companyService.getCompanies(pageable);
    }

    @GetMapping(value = "/report")
    public byte[] incomeReport(ReportDateFilter filter,
                               HttpServletResponse response) throws IOException {
        InputStream clients = reportService.getClientsStatistics(filter);
        setExcelHeaders(response, "clients report.xlsx");
        return inputStreamToByteArrAndClose(clients);
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

    @GetMapping("/{companyId}")
    public String getCompanyLogo(@PathVariable long companyId) {
        return companyService.getCompanyLogoById(companyId);
    }

    private void setExcelHeaders(HttpServletResponse response, String filename) {
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader(HttpHeaders.CONTENT_TYPE, "attachment; filename=\"" + filename + "\"");
    }

    private byte[] inputStreamToByteArrAndClose(InputStream inputStream) throws IOException {
        byte[] bytes = IOUtils.toByteArray(inputStream);
        inputStream.close();
        return bytes;
    }
}
