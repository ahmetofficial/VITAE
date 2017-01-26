package excelleditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcellEditor_CreateInsertScript {

    public static void main(String[] args) throws IOException {

        InputStream fis = new FileInputStream("C:\\Users\\SAMSUNG\\Desktop\\deneme.xls");
        POIFSFileSystem poiFs = new POIFSFileSystem(fis);
        Workbook wb = new HSSFWorkbook(poiFs);
        int totalNoOfSheets = wb.getNumberOfSheets();
        Sheet sheet = wb.getSheetAt(0);
        int totalNoOfRows = sheet.getPhysicalNumberOfRows();
        int amount = 0;
        TreeSet<String> x = new TreeSet<>();

        for (int i = 0; i < totalNoOfRows; i++) {
            Row row = sheet.getRow(i);
            Cell id = row.getCell(5);
            String formOfDrug = id.toString();
            int formId = 0;
            x.add(formOfDrug);
            /*
            switch(formOfDrug){
                case "Tablet": formId=1; break;
                case "Efervesan tablet": formId=2; break;
                case "Kapsül": formId=3; break;
                case "Flakon": formId=4; break;
                case "Ampul": formId=5; break;
                case "Krem": formId=6; break;
                case "Merhem": formId=7; break;
                case "Şampuan": formId=8; break;
                case "Paket": formId=9; break;
                case "Toz": formId=10; break;
                case "Transdermal sistem": formId=11; break;
                case "İmplant sistem": formId=12; break;
                case "İnhaler sistem": formId=13; break;
                case "Süspansiyon": formId=14; break;
                case "Şurup": formId=15; break;
                case "Supozituar (fitil)": formId=16; break;
                case "Losyon": formId=17; break;
                case "Emülsiyon": formId=18; break;
                case "Damla": formId=19; break;
                case "Sprey": formId=20; break;
                case "Vajinal suppozituar": formId=21; break;
                case "Granül": formId=22; break;
                case "Diğer": formId=23; break;
            }
             */

        }
        
//        fis.close();
//        FileOutputStream fos = new FileOutputStream("C:\\Users\\SAMSUNG\\Desktop\\deneme.xls");
//        wb.write(fos);
//        fos.close();
//        System.out.println(amount + " kadar eleman oluşturuldu.");

    }
}
