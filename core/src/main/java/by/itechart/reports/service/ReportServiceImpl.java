package by.itechart.reports.service;

import by.itechart.commoditylot.repository.InputGoodsStatistics;
import by.itechart.commoditylot.service.CommodityLotService;
import by.itechart.company.entity.CompanyAction;
import by.itechart.company.service.CompanyService;
import by.itechart.profit.repository.PaymentStatistics;
import by.itechart.profit.service.PaymentService;
import by.itechart.reports.dto.ReportDateFilter;
import by.itechart.writeoffact.repository.PersonalLossStatistics;
import by.itechart.writeoffact.repository.WriteOffStatistics;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
    private final CommodityLotService commodityLotService;
    private final WriteOffActService writeOffActService;
    private final PaymentService paymentService;
    private final CompanyService companyService;

    private final LocalDateTime MAX_DATE_TIME = LocalDateTime.of(2100, 1, 1, 0, 0, 0, 0);
    private final LocalDate MAX_DATE = LocalDate.of(2100, 1, 1);
    private final LocalDate MIN_DATE = LocalDate.of(2000, 1, 1);

    @Autowired
    public ReportServiceImpl(CommodityLotService commodityLotService,
                             WriteOffActService writeOffActService,
                             PaymentService paymentService,
                             CompanyService companyService) {
        this.commodityLotService = commodityLotService;
        this.writeOffActService = writeOffActService;
        this.paymentService = paymentService;
        this.companyService = companyService;
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

    @Override
    public InputStream getPaymentStatistics(Long companyId, ReportDateFilter filter) throws IOException {
        File file = ResourceUtils.getFile("classpath:exceltemplates/profit report.xlsx");
        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        ReportDateFilter checkFilter = checkFilter(filter);
        List<PaymentStatistics> paymentStatistics = paymentService.getPaymentStatistics(checkFilter, companyId);

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

        for (int i = 0; i < paymentStatistics.size(); i++) {
            row = sheet.createRow(rownum);
            rownum++;
            //A
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(i + 1);
            //B
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(paymentStatistics.get(i).getDateMonth() + "-" + paymentStatistics.get(i).getDateYear());
            //C
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(paymentStatistics.get(i).getTotal());
        }

        Name persons = workbook.getName("mount");
        String reference = "datasheet!$B$4:$B$" + (paymentStatistics.size() + 3);
        persons.setRefersToFormula(reference);

        Name amount = workbook.getName("profit");
        reference = "datasheet!$C$4:$C$" + (paymentStatistics.size() + 3);
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

        FileOutputStream fileOut = new FileOutputStream(System.getProperty("java.io.tmpdir") + File.separator + "profit report" + companyId + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        return new FileInputStream(System.getProperty("java.io.tmpdir") + File.separator + "profit report" + companyId + ".xlsx");
    }

    @Override
    public InputStream getWriteOffStatistics(Long companyId, ReportDateFilter filter) throws IOException {
        ReportDateFilter checkedFilter = checkFilter(filter);

        List<WriteOffStatistics> writeOffStatistics = writeOffActService.getWriteOffStatistics(companyId, checkedFilter);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("datasheet");

        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Report period:");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue((filter.getFrom() == null ? "" : filter.getFrom().toString()));

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue((filter.getTo() == null ? "" : filter.getTo().toString()));

        rownum++;
        row = sheet.createRow(rownum);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Responsible person");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Write-off type");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Goods");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Placement");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Measurement unit");

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Cost");

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Weight");

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Count");

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Total cost");

        cell = row.createCell(10, CellType.STRING);
        cell.setCellValue("Total weight");

        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue(" Labelling");

        cell = row.createCell(12, CellType.STRING);
        cell.setCellValue("Description");

        int counter = 1;

        // Data
        for (WriteOffStatistics value : writeOffStatistics) {
            rownum++;
            row = sheet.createRow(rownum);

            //A
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(counter);
            //B
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(value.getResponsiblePerson());
            //C
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(value.getWriteOffType());
            //D
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(value.getGoodsName());
            //E
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(value.getGoodsPlacementType().toString());
            //F
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(value.getGoodsMeasurementUnitType().toString());
            //G
            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue(value.getCost());
            //H
            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue(value.getWeight());
            //I
            cell = row.createCell(8, CellType.NUMERIC);
            cell.setCellValue(value.getAmount());
            //J
            String formula = "$G$" + (rownum + 1) + "*$I$" + (rownum + 1);
            cell = row.createCell(9, CellType.FORMULA);
            cell.setCellFormula(formula);
            //K
            formula = "$H$" + (rownum + 1) + "*$I$" + (rownum + 1);
            cell = row.createCell(10, CellType.FORMULA);
            cell.setCellFormula(formula);
            //L
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue(value.getLabelling());
            //M
            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue(value.getDescription());

            counter++;
        }

        rownum++;
        row = sheet.createRow(rownum);

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Total:");

        String formula = "SUM($I$" + (rownum + 1 - writeOffStatistics.size()) + ":$I$" + (rownum) + ")";
        cell = row.createCell(8, CellType.FORMULA);
        cell.setCellFormula(formula);

        formula = "SUM($J$" + (rownum + 1 - writeOffStatistics.size()) + ":$J$" + (rownum) + ")";
        cell = row.createCell(9, CellType.FORMULA);
        cell.setCellFormula(formula);

        formula = "SUM($K$" + (rownum + 1 - writeOffStatistics.size()) + ":$K$" + (rownum) + ")";
        cell = row.createCell(10, CellType.FORMULA);
        cell.setCellFormula(formula);

        for (int i = 0; i < 13; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream(System.getProperty("java.io.tmpdir") + File.separator + "write-off report" + companyId + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        return new FileInputStream(System.getProperty("java.io.tmpdir") + File.separator + "write-off report" + companyId + ".xlsx");
    }

    @Override
    public InputStream getClientsStatistics(ReportDateFilter filter) throws IOException {
        File file = ResourceUtils.getFile("classpath:exceltemplates/clients report.xlsx");
        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        ReportDateFilter checkFilter = checkFilter(filter);

        List<CompanyAction> clientsStatistics = companyService.getClientsStatistics(checkFilter);

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

        rownum++;

        int startClientsCount = 0;
        int endClientsCount = 0;

        for (CompanyAction clientsStatistic : clientsStatistics) {
            if (clientsStatistic.getEnd().toLocalDate().isAfter(filter.getTo()) || clientsStatistic.getEnd().toLocalDate().equals(filter.getTo())) {
                endClientsCount++;
            } else if (clientsStatistic.getStart().toLocalDate().isBefore(filter.getFrom()) || clientsStatistic.getStart().toLocalDate().equals(filter.getFrom())) {
                startClientsCount++;
            }
        }

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Count of clients:");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(startClientsCount);

        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue(endClientsCount);

        rownum = 3;

        HashMap<String, Integer> lostClientCountByMonth = new HashMap<>();
        HashMap<String, Integer> newClientCountByMonth = new HashMap<>();
//        HashMap<String, Integer> clientProfitByMonth = new HashMap<>();

        for (CompanyAction clientsStatistic : clientsStatistics) {
            if (!clientsStatistic.getEnd().equals(MAX_DATE_TIME)) {
                int monthValue = clientsStatistic.getEnd().getMonthValue();
                int year = clientsStatistic.getEnd().getYear();
                putOrUpdateMapKeyValue(lostClientCountByMonth, generateStringFromMonthAndYear(monthValue, year), 1);
            }
            if (clientsStatistic.getStart().toLocalDate().isAfter(filter.getFrom())) {
                int monthValue = clientsStatistic.getStart().getMonthValue();
                int year = clientsStatistic.getStart().getYear();
                putOrUpdateMapKeyValue(newClientCountByMonth, generateStringFromMonthAndYear(monthValue, year), 1);
            }

//            long between = ChronoUnit.MONTHS.between(clientsStatistic.getStart().toLocalDate(), clientsStatistic.getEnd().toLocalDate() == MAX_DATE ? LocalDate.now() : clientsStatistic.getEnd().toLocalDate());
//            initProfitMap(clientProfitByMonth, between, clientsStatistic.getStart().toLocalDate());
//            Period between = Period.between(clientsStatistic.getStart().toLocalDate(), clientsStatistic.getEnd().toLocalDate());
//            int months = between.getMonths();
//            between.ge
//            for (int i = 0; i < months; i++) {
//
//            }

        }

        Set<String> lostMounts = lostClientCountByMonth.keySet();
        Set<String> newMounts = newClientCountByMonth.keySet();

        Set<String> allMounts = new HashSet<>();
        allMounts.addAll(newMounts);
        allMounts.addAll(lostMounts);

        List<String> allMountsList = new ArrayList<>(allMounts);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        allMountsList.sort(Comparator.comparing(s -> LocalDate.parse(s + "-01", formatter)));

        for (String mount : allMountsList) {
            row = sheet.createRow(rownum);
            rownum++;
            //A // mount
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(mount);
            //B
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("New");
            //C
            int newClients = newClientCountByMonth.get(mount) == null ? 0 : newClientCountByMonth.get(mount);
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(newClients);

            row = sheet.createRow(rownum);
            rownum++;
            //B
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Lost");
            //C
            int lostClients = lostClientCountByMonth.get(mount) == null ? 0 : lostClientCountByMonth.get(mount);
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(lostClients);

            row = sheet.createRow(rownum);
            rownum++;
            //B
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Total");
            //C
            startClientsCount = startClientsCount + newClients - lostClients;
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(startClientsCount);
        }

        Name count = workbook.getName("count");
        String reference = "datasheet!$C$3:$C$" + (rownum + 1);
        count.setRefersToFormula(reference);

        Name monthAndClients = workbook.getName("monthAndClients");
        reference = "datasheet!$A$3:$B$" + (rownum + 1);
        monthAndClients.setRefersToFormula(reference);

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        inputStream.close();

        FileOutputStream fileOut = new FileOutputStream(System.getProperty("java.io.tmpdir") + File.separator + "clients report.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        return new FileInputStream(System.getProperty("java.io.tmpdir") + File.separator + "clients report.xlsx");
    }

//    private void initProfitMap(HashMap<String, Integer> clientProfitByMonth, long countOfMonth, LocalDate startDate) {
//        int monthValue = startDate.getMonthValue();
//        int year = startDate.getYear();
//        putOrUpdateMapKeyValue(clientProfitByMonth, generateStringFromMonthAndYear(monthValue, year), 0);
//        for (int i = 0; i < countOfMonth; i++) {
//            monthValue
//        }
//    }


    private ReportDateFilter checkFilter(ReportDateFilter filter) {
        if (filter.getFrom() == null) {
            filter.setFrom(MIN_DATE);
        }
        if (filter.getTo() == null) {
            filter.setTo(MAX_DATE);
        }
        return filter;
    }

    private void putOrUpdateMapKeyValue(Map<String, Integer> map, String key, Integer value) {
        if (map.containsKey(key)) {
            map.replace(key, map.get(key) + value);
        } else map.put(key, value);
    }

    private String generateStringFromMonthAndYear(int month, int year) {
        return year + "-" + (month < 10 ? ("0" + month) : month);
    }
}
