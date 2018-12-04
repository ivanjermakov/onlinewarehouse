package by.itechart.profit.service;

import by.itechart.profit.PaymentAct;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ExcelOutputService {

    private final PaymentService paymentService;

    public ExcelOutputService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    public OutputStream writeProftIntoExcel(LocalDateTime start, LocalDateTime end) throws FileNotFoundException, IOException {

        List<PaymentAct> paymentActs = paymentService.getPaymentForPeriod(start, end);
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("ProfitStats");

        long weeks = ChronoUnit.WEEKS.between(start, end);


        Row row;
        Cell cell;

        row = sheet.createRow(0);
        row.createCell(0);
        row.createCell(1).setCellValue("HEADER 1");
        row.createCell(2).setCellValue("HEADER 2");
        row.createCell(3).setCellValue("HEADER 3");

        for (int r = 0; r < weeks; r++) {
            row = sheet.createRow(r);
            cell = row.createCell(0);
            cell.setCellValue(r);
            final int k = r;
            Long profitValue = paymentActs.stream()
                    .filter(paymentAct -> {
                        return start.plusWeeks(k).isBefore(paymentAct.getPaymentDate())
                                && start.plusWeeks(k+1).isAfter(paymentAct.getPaymentDate());
                    }).map(paymentAct -> paymentAct.getRateExacted().getRate()).mapToLong(i -> i).sum();
            cell = row.createCell(1);
            cell.setCellValue(profitValue);
            Long damageValue = paymentService.getDamages(start.toLocalDate(), end.toLocalDate());
            cell = row.createCell(2);
            cell.setCellValue(damageValue);
            cell = row.createCell(3);
            cell.setCellValue(profitValue - damageValue);
        }

        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 8, 20);

        org.apache.poi.ss.usermodel.Chart chart = drawing.createChart(anchor);

        CTChart ctChart = ((XSSFChart)chart).getCTChart();
        CTPlotArea ctPlotArea = ctChart.getPlotArea();
        CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
        CTBoolean ctBoolean = ctBarChart.addNewVaryColors();
        ctBoolean.setVal(true);
        ctBarChart.addNewBarDir().setVal(STBarDir.COL);

        for (int r = 2; r < 6; r++) {
            CTBarSer ctBarSer = ctBarChart.addNewSer();
            CTSerTx ctSerTx = ctBarSer.addNewTx();
            CTStrRef ctStrRef = ctSerTx.addNewStrRef();
            ctStrRef.setF("ProfitStats!$A$" + r);
            ctBarSer.addNewIdx().setVal(r-2);
            CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
            ctStrRef = cttAxDataSource.addNewStrRef();
            ctStrRef.setF("ProfitStats!$B$1:$D$1");
            CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
            ctNumRef.setF("ProfitStats!$B$" + r + ":$D$" + r);

            //at least the border lines in Libreoffice Calc ;-)
            ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});

        }

        //telling the BarChart that it has axes and giving them Ids
        ctBarChart.addNewAxId().setVal(123456);
        ctBarChart.addNewAxId().setVal(123457);

        //cat axis
        CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
        ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
        CTScaling ctScaling = ctCatAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctCatAx.addNewDelete().setVal(false);
        ctCatAx.addNewAxPos().setVal(STAxPos.B);
        ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

        //val axis
        CTValAx ctValAx = ctPlotArea.addNewValAx();
        ctValAx.addNewAxId().setVal(123457); //id of the val axis
        ctScaling = ctValAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctValAx.addNewDelete().setVal(false);
        ctValAx.addNewAxPos().setVal(STAxPos.L);
        ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

        //legend
        CTLegend ctLegend = ctChart.addNewLegend();
        ctLegend.addNewLegendPos().setVal(STLegendPos.B);
        ctLegend.addNewOverlay().setVal(false);

        System.out.println(ctChart);

        FileOutputStream fileOut = new FileOutputStream("Stats.xlsx");
        wb.write(fileOut);
        fileOut.close();
        return fileOut;
    }
}
