package by.itechart.web.controller;

import by.itechart.reports.dto.ReportDateFilter;
import by.itechart.reports.service.ReportService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@RestController
@RequestMapping("/companies/{companyId}/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/income-report")
    public byte[] incomeReport(@PathVariable long companyId,
                               ReportDateFilter filter,
                               HttpServletResponse response) throws IOException {
        InputStream incomeGoods = reportService.getIncomeGoods(companyId, filter);
        setExcelHeaders(response, "income report.xlsx");
        return inputStreamToByteArrAndClose(incomeGoods);
    }

    @GetMapping(value = "/personal-loss-report")
    public byte[] personalLossReport(@PathVariable long companyId,
                                     ReportDateFilter filter,
                                     HttpServletResponse response) throws IOException {
        InputStream personalLoss = reportService.getPersonalLoss(companyId, filter);
        setExcelHeaders(response, "personal loss report.xlsx");
        return inputStreamToByteArrAndClose(personalLoss);
    }

    @GetMapping
    public ResponseEntity getClientsReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "filename=clientsReport.xls");
        // xls report with amount of clients, new and lost clients
        return ResponseEntity.status(HttpStatus.OK).headers(fileHeaders).body(new byte[100]);
    }

    @GetMapping("/warehouses/{warehouseId}/arrival")
    public ResponseEntity getWarehouseArrival(@PathVariable long companyId, @PathVariable long warehouseId,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=warehouseArrival.xls");
        // xls report with arrived goods in warehouse
        return ResponseEntity.status(HttpStatus.OK).headers(fileHeaders).body(new byte[100]);
    }

    @GetMapping("/{companyId}/warehouses/{warehouseId}/loss/act")
    public ResponseEntity getWarehouseLossByAct(@PathVariable long companyId, @PathVariable long warehouseId,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=warehouseLossAct.xls");
        // xls report with loss goods in warehouse by acts
        return ResponseEntity.status(HttpStatus.OK).headers(fileHeaders).body(new byte[100]);
    }

    @GetMapping("/{companyId}/warehouses/{warehouseId}/loss/responsible-person")
    public ResponseEntity getWarehouseLossByResponsiblePerson(@PathVariable long companyId, @PathVariable long warehouseId,
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=warehouseLossResponsiblePerson.xls");
        // xls report with loss goods in warehouse by responsible persons
        return ResponseEntity.status(HttpStatus.OK).headers(fileHeaders).body(new byte[100]);
    }

    @GetMapping("/{companyId}/warehouses/{warehouseId}/profit")
    public ResponseEntity getWarehouseProfit(@PathVariable long companyId, @PathVariable long warehouseId,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=warehouseProfit.xls");
        // xls report with profit of warehouse
        return ResponseEntity.status(HttpStatus.OK).headers(fileHeaders).body(new byte[100]);
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
