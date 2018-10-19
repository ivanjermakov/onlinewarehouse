package by.itechart.web.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/reports/companies")
public class ReportController {

    @GetMapping
    public ResponseEntity getClientsReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "filename=clientsReport.xls");
        // xls report with amount of clients, new and lost clients
        return ResponseEntity.status(HttpStatus.OK).headers(fileHeaders).body(new byte[100]);
    }

    @GetMapping("/{companyId}/warehouse/{warehouseId}/arrival")
    public ResponseEntity getWarehouseArrival(@PathVariable long companyId, @PathVariable long warehouseId,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=warehouseArrival.xls");
        // xls report with arrived goods in warehouse
        return ResponseEntity.status(HttpStatus.OK).headers(fileHeaders).body(new byte[100]);
    }

    @GetMapping("/{companyId}/warehouse/{warehouseId}/loss/act")
    public ResponseEntity getWarehouseLossByAct(@PathVariable long companyId, @PathVariable long warehouseId,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=warehouseLossAct.xls");
        // xls report with loss goods in warehouse by acts
        return ResponseEntity.status(HttpStatus.OK).headers(fileHeaders).body(new byte[100]);
    }

    @GetMapping("/{companyId}/warehouse/{warehouseId}/loss/responsible-person")
    public ResponseEntity getWarehouseLossByResponsiblePerson(@PathVariable long companyId, @PathVariable long warehouseId,
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=warehouseLossResponsiblePerson.xls");
        // xls report with loss goods in warehouse by responsible persons
        return ResponseEntity.status(HttpStatus.OK).headers(fileHeaders).body(new byte[100]);
    }

    @GetMapping("/{companyId}/warehouse/{warehouseId}/profit")
    public ResponseEntity getWarehouseProfit(@PathVariable long companyId, @PathVariable long warehouseId,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=warehouseProfit.xls");
        // xls report with profit of warehouse
        return ResponseEntity.status(HttpStatus.OK).headers(fileHeaders).body(new byte[100]);
    }


}
