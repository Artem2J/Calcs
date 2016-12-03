import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Sheet;


/**
 * Created by 2jart on 27.11.2016.
 */

public class CalcsEntry {

    List<String> list = new ArrayList<>();

    public static Map<String, Calc> getCalcs() {

        return calcs;
    }

    static Map<String, Calc> calcs = new HashMap<>();

    public static Map<String, Shop> getShops() {
        return shops;
    }

    static Map<String, Shop> shops = new HashMap<>();
    public CalcsEntry(String fileName) throws IOException {

        //String fileNameXLS = "src/in/calcs/1.xls";
        //HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(fileNameXLS));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "CP1251"));
        boolean flag1 = false;
        boolean flag2 = false;
        String stringValue = "";
        String calcCode = "";
        while (bufferedReader.ready()){
            stringValue = bufferedReader.readLine();
            if (stringValue.contains("Розничная продажа")){
                flag1 = true;
                stringValue = bufferedReader.readLine();
            }
            if (stringValue.contains("Оптовая продажа")){
                flag1 = false;
                flag2 = true;
                stringValue = bufferedReader.readLine();
            }
            if (stringValue.contains("ИТОГО"))flag2 = false;
            if (flag1){
                if (stringValue.charAt(0)!=','){
                    calcCode = stringValue.split(",")[0];
                    addToCalcList(stringValue);
                }else {
                    addToShopList(calcCode, stringValue);
                }

            }
            if (flag2){
                if (stringValue.charAt(0)!=','){
                    addToCalcList(stringValue);
                }
                list.add(stringValue);
            }
        }
        //Sheet sheet = myExcelBook.getSheetAt(myExcelBook.getActiveSheetIndex());
        System.out.print("UUUaaauuu");

    }

    private void addToShopList(String calcCode, String stringValue) {
        String shopName = "";
        int count = 0;
        if (stringValue.contains("ZОтчет 2/") || stringValue.contains("ZОтчет 1/") || stringValue.contains("ZОтчет 3/"))shopName = "Выставочный зал";
        else if (stringValue.contains("ZОтчет 4/") || stringValue.contains("ZОтчет 5/")) shopName = "Бизнесцентр";
        else if (stringValue.contains("ZОтчет 6/") || stringValue.contains("ZОтчет 9/")) shopName = "Суворов";
        else if (stringValue.contains("ZОтчет 7/") || stringValue.contains("ZОтчет 8/")) shopName = "Ефремов";
        else if (stringValue.contains("ZОтчет 10/")) shopName = "Престиж";
        else if (stringValue.contains("ZОтчет 11/")) shopName = "Донской";
        else if (stringValue.contains("ZОтчет 12/")) shopName = "Ложевая";
        else if (stringValue.contains("ZОтчет 13/") || stringValue.contains("ZОтчет 14/") || stringValue.contains("ZОтчет 15/")) shopName = "Лота";
        else if (stringValue.contains("ZОтчет 16/") || stringValue.contains("ZОтчет 17/")) shopName = "Новый Евремов";
        else if (stringValue.contains("ZОтчет 18/")) shopName = "Руднева";
        else if (stringValue.contains("ZОтчет 19/")) shopName = "Кирова";
        else if (stringValue.contains("ZОтчет 20/"))shopName = "Алексин";
        else if (stringValue.contains("ZОтчет 51"))shopName = "Мясново";
        else{
            System.out.print("Неопозднаный магазин!!!!!!!!!!!!");
            shopName = "Unknow_shop";
        }
        count = Integer.parseInt(stringValue.split("\",\"")[1].split(",")[0]);
        if (!shops.containsKey(shopName)){
            shops.put(shopName, new Shop(shopName));
            shops.get(shopName).addSale(calcCode, count);
        }else {
            shops.get(shopName).addSale(calcCode, count);
        }
    }

    private void addToCalcList(String stringValue) {
        String code = stringValue.split(",")[0];
        String name = stringValue.split(",")[1];
        int count = Integer.parseInt(stringValue.split("шт")[0].split("\",\"")[2].split(",")[0]);
        if (calcs.containsKey(code)){
            count += calcs.get(code).getNamber();
            calcs.get(code).setNamber(count);
        }else {
            calcs.put(code, new Calc(code, name, count));
        }
    }
}

