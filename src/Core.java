import java.io.IOException;

/**
 * Created by 2jart on 26.11.2016.
 */
public class Core {

    public static void main(String[] args) throws IOException {

        MyFrame myFrame = new MyFrame();
        String fileName = myFrame.fileName;
        myFrame.doLayout();
        new CalcsEntry(fileName);
        String fileReportName = fileName.substring(0, fileName.indexOf(".csv")) + "_processed.xls";
        CreateXlsReport.writeIntoExcel(fileReportName);
    }
}
