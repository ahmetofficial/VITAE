package excelleditor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcellEditor_DeleteDrugsFromList {

    
    public static void main(String[] args) throws IOException {
        InputStream fis = new FileInputStream("C:\\Users\\SAMSUNG\\Downloads\\Human_Drug_List_May_2014_V1_Web.xls");
        POIFSFileSystem poiFs = new POIFSFileSystem(fis);
        Workbook wb = new HSSFWorkbook(poiFs);
        int totalNoOfSheets = wb.getNumberOfSheets();
        Sheet sheet = wb.getSheetAt(0);
        int totalNoOfRows = sheet.getPhysicalNumberOfRows();
        
        for (int i = 0; i < totalNoOfRows; i++) {
            Row row = sheet.getRow(i);
            Cell id=row.getCell(0);
            if(id.toString().equals("boş")){
                sheet.removeRow(sheet.getRow(i));
            }
        }

//        fis.close();
//        FileOutputStream fos = new FileOutputStream("C:\\Users\\SAMSUNG\\Downloads\\Human_Drug_List_May_2014_V1_Web.xls");
//        wb.write(fos);
//        fos.close();
    }
}
