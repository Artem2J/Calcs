import javafx.util.Pair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by 2jart on 01.12.2016.
 */
public class CreateXlsReport {

        public static void writeIntoExcel(String file) throws FileNotFoundException, IOException {
            int calcsCount = 0;
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("Calcs");
            Row row = sheet.createRow(calcsCount++);
            Cell code = row.createCell(1);
            code.setCellValue("Код");
            Cell name = row.createCell(2);
            name.setCellValue("Наименование");
            Cell count = row.createCell(3);
            count.setCellValue("Количество");
            Map<String, Calc> calcXls = CalcsEntry.getCalcs();
            Set<String> calcsList = calcXls.keySet();
            for (String calc: calcsList){
                row = sheet.createRow(calcsCount++);
                code = row.createCell(1);
                code.setCellValue(calcXls.get(calc).getCode());
                name = row.createCell(2);
                name.setCellValue(calcXls.get(calc).getName());
                count = row.createCell(3);
                count.setCellValue(calcXls.get(calc).getNamber());
            }
            //sheet.createRow(1).createCell(2).setCellValue("12");
            //sheet.createRow(0).createCell(3).setCellValue("124");

            // Нумерация лет начинается с 1900-го
            //birthdate.setCellValue(new Date(110, 10, 10));

            // Меняем размер столбца
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);

            // расписываем магазины

            Set<String> shopList = CalcsEntry.getShops().keySet();
            for (String shop: shopList){
                calcsCount = 0;
                sheet = book.createSheet(shop);
                row = sheet.createRow(calcsCount++);
                Cell calcCode = row.createCell(1);
                Cell calcName = row.createCell(2);
                Cell calcCount = row.createCell(3);
                calcCode.setCellValue("Код");
                calcName.setCellValue("Наименомание");
                calcCount.setCellValue("Количество");
                Map<Calc, Integer> calcForXls = CalcsEntry.getShops().get(shop).getSaleCalcs();
                for (Calc calc: calcForXls.keySet()){
                    row = sheet.createRow(calcsCount++);
                    calcCode = row.createCell(1);
                    calcName = row.createCell(2);
                    calcCount = row.createCell(3);
                    calcCode.setCellValue(calc.getCode());
                    calcName.setCellValue(calc.getName());
                    calcCount.setCellValue(calcForXls.get(calc));
                }
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
            }
            // Записываем всё в файл
            book.write(new FileOutputStream(file));
            book.close();

        }
}

