package by.itechart.reports.service;

import by.itechart.commoditylot.repository.InputGoodsStatistics;
import by.itechart.commoditylot.service.CommodityLotService;
import by.itechart.reports.dto.ReportDateFilter;
import by.itechart.writeoffact.repository.PersonalLossStatistics;
import by.itechart.writeoffact.service.WriteOffActService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
    private final CommodityLotService commodityLotService;
    private final WriteOffActService writeOffActService;

    @Autowired
    public ReportServiceImpl(CommodityLotService commodityLotService, WriteOffActService writeOffActService) {
        this.commodityLotService = commodityLotService;
        this.writeOffActService = writeOffActService;
    }

    public InputStream getIncomeGoods(Long companyId, ReportDateFilter filter) throws IOException {

        ReportDateFilter checkedFilter = checkFilter(filter);

        List<InputGoodsStatistics> incomeStatistics = commodityLotService.getIncomeStatistics(companyId, checkedFilter);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Income goods");

        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Report period:" +
                (filter.getFrom() == null ? "" : filter.getFrom().toString()) + " - " +
                (filter.getTo() == null ? "" : filter.getTo().toString()));

        rownum++;
        row = sheet.createRow(rownum);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Counterparty name");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Tax number");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Goods");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Placement");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Measurement unit");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Cost");

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Weight");

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Count");

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Total cost");

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Total weight");

        cell = row.createCell(10, CellType.STRING);
        cell.setCellValue(" Labelling");

        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("Description");

        // Data
        for (InputGoodsStatistics value : incomeStatistics) {
            rownum++;
            row = sheet.createRow(rownum);

            //A
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(value.getCounterpartyName());
            //B
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(value.getCounterpartyTaxNumber());
            //C
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(value.getGoodsName());
            //D
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(value.getGoodsPlacementType().toString());
            //E
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(value.getGoodsMeasurementUnitType().toString());
            //F
            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellValue(value.getCost());
            //G
            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue(value.getWeight());
            //H
            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue(value.getAmount());
            //I
            String formula = "$F$" + (rownum + 1) + "*$H$" + (rownum + 1);
            cell = row.createCell(8, CellType.FORMULA);
            cell.setCellFormula(formula);
            //J
            formula = "$G$" + (rownum + 1) + "*$H$" + (rownum + 1);
            cell = row.createCell(9, CellType.FORMULA);
            cell.setCellFormula(formula);
            //K
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue(value.getLabelling());
            //L
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue(value.getDescription());
        }

        rownum++;
        row = sheet.createRow(rownum);

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Total:");

        String formula = "SUM($H$" + (rownum + 1 - incomeStatistics.size()) + ":$H$" + (rownum) + ")";
        cell = row.createCell(7, CellType.FORMULA);
        cell.setCellFormula(formula);

        formula = "SUM($I$" + (rownum + 1 - incomeStatistics.size()) + ":$I$" + (rownum) + ")";
        cell = row.createCell(8, CellType.FORMULA);
        cell.setCellFormula(formula);

        formula = "SUM($G$" + (rownum + 1 - incomeStatistics.size()) + ":$G$" + (rownum) + ")";
        cell = row.createCell(9, CellType.FORMULA);
        cell.setCellFormula(formula);

        for (int i = 0; i < 12; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream(System.getProperty("java.io.tmpdir") + File.separator + "income report" + companyId + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        return new FileInputStream(System.getProperty("java.io.tmpdir") + File.separator + "income report" + companyId + ".xlsx");
    }

    @Override
    public InputStream getPersonalLoss(Long companyId, ReportDateFilter filter) throws IOException {
        File file = ResourceUtils.getFile("classpath:exceltemplates/personal loss report.xlsx");
        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        ReportDateFilter checkFilter = checkFilter(filter);
        List<PersonalLossStatistics> personalLossStatistics = writeOffActService.getPersonalLossStatistics(companyId, checkFilter);

        XSSFSheet sheet = workbook.getSheet("datasheet");

        int rownum = 0;

        XSSFRow row;
        XSSFCell cell;

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Report period:");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue((filter.getFrom() == null ? "" : filter.getFrom().toString()));

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue((filter.getTo() == null ? "" : filter.getTo().toString()));

        rownum = 3;

        for (int i = 0; i < personalLossStatistics.size(); i++) {
            row = sheet.createRow(rownum);
            rownum++;
            //A
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(i + 1);
            //B
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(personalLossStatistics.get(i).getResponsiblePerson());
            //C
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(personalLossStatistics.get(i).getTotal());
        }

        Name persons = workbook.getName("persons");
        String reference = "datasheet!$B$4:$B$" + (personalLossStatistics.size() + 3);
        persons.setRefersToFormula(reference);

        Name amount = workbook.getName("amount");
        reference = "datasheet!$C$4:$C$" + (personalLossStatistics.size() + 3);
        amount.setRefersToFormula(reference);

        row = sheet.createRow(rownum);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Total:");

        String formula = "SUM($C$4:$C$" + (rownum) + ")";
        cell = row.createCell(2, CellType.FORMULA);
        cell.setCellFormula(formula);

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        inputStream.close();

        FileOutputStream fileOut = new FileOutputStream(System.getProperty("java.io.tmpdir") + File.separator + "personal loss report" + companyId + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        return new FileInputStream(System.getProperty("java.io.tmpdir") + File.separator + "personal loss report" + companyId + ".xlsx");
    }

    public void test(Long companyId, LocalDate from, LocalDate to) throws IOException {
//        File file = ResourceUtils.getFile("classpath:exceltemplates/personal loss report.xlsx");
////        String separator = File.separator;
////        System.out.println(separator);
////        String path = "exceltemplates\\personal loss report.xlsx";
////        System.out.println(path);
////        File fileFromResources = getExcelTemplateFromResources(path);
//        if (true) {
//            FileInputStream inputStream = new FileInputStream(file);
//
//            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//
//            XSSFSheet sheet = workbook.getSheet("datasheet");
//
//            XSSFRow row = sheet.createRow(4);
//            XSSFCell cell;
//
//            //A
//            cell = row.createCell(0, CellType.STRING);
//            cell.setCellValue("Person 4");
//            //B
//            cell = row.createCell(1, CellType.NUMERIC);
//            cell.setCellValue(15);
//
//            Name persons = workbook.getName("persons");
//            String reference = "datasheet!$A$2:$A$5";
////            persons.setNameName("persons");
//            persons.setRefersToFormula(reference);
//
//            Name amount = workbook.getName("amount");
//            reference = "datasheet!$B$2:$B$5";
//            amount.setRefersToFormula(reference);
////            amount.setNameName("amount");
////            persons.setRefersToFormula(reference);
//
//            inputStream.close();
//
//            FileOutputStream fileOut = new FileOutputStream(System.getProperty("java.io.tmpdir") + File.separatorChar + "personal loss report.xlsx");
//            workbook.write(fileOut);
//            fileOut.close();
//        }

//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("Income goods");
//
//        int rownum = 0;
//        Cell cell;
//        Row row;
//
//        row = sheet.createRow(rownum);
//        cell = row.createCell(0, CellType.STRING);
//        cell.setCellValue("Report period:" + from.toString() + " - " + to.toString());
//
//        rownum++;
//        row = sheet.createRow(rownum);
//
//        cell = row.createCell(0, CellType.STRING);
//        cell.setCellValue("Counterparty name");
//
//        cell = row.createCell(1, CellType.STRING);
//        cell.setCellValue("Tax number");
//
//        cell = row.createCell(2, CellType.STRING);
//        cell.setCellValue("Goods");
//
//        cell = row.createCell(3, CellType.STRING);
//        cell.setCellValue("Placement");
//
//        cell = row.createCell(4, CellType.STRING);
//        cell.setCellValue("Measurement unit");
//
//        cell = row.createCell(5, CellType.STRING);
//        cell.setCellValue("Count");
//
//        cell = row.createCell(6, CellType.STRING);
//        cell.setCellValue("Cost");
//
//        cell = row.createCell(7, CellType.STRING);
//        cell.setCellValue("Weight");
//
//        cell = row.createCell(8, CellType.STRING);
//        cell.setCellValue("Labelling");
//
//        cell = row.createCell(9, CellType.STRING);
//        cell.setCellValue("Description");
//
//        FileOutputStream fileOut = new FileOutputStream(System.getProperty("java.io.tmpdir") + "test.xlsx");
//        workbook.write(fileOut);
//        fileOut.close();
    }

    private ReportDateFilter checkFilter(ReportDateFilter filter) {
        if (filter.getFrom() == null) {
            filter.setFrom(LocalDate.of(2000, 1, 1));
        }
        if (filter.getTo() == null) {
            filter.setTo(LocalDate.of(2100, 1, 1));
        }
        return filter;
    }
}
