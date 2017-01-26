package scriptbuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import javafx.scene.control.Cell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class DrugXLStoDrugDatabase {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fis = new FileInputStream("C:\\Users\\SAMSUNG\\Desktop\\TC-Sağlık Bakanlığı Düzenlemiş ATC İlaç Tablosu.xls");
        POIFSFileSystem poiFs = new POIFSFileSystem(fis);
        Workbook wb = new HSSFWorkbook(poiFs);
        int totalNoOfSheets = wb.getNumberOfSheets();
        Sheet sheet = wb.getSheetAt(0);
        int totalNoOfRows = sheet.getPhysicalNumberOfRows();
        int amount = 0;

        HashMap<Integer, ArrayList> map = new HashMap<>();
        for (int i = 0; i < totalNoOfRows; i++) {
            Row row = sheet.getRow(i);
            ArrayList<String> temp = new ArrayList<>();
            temp.add(String.valueOf(i + 1));
            temp.add(row.getCell(0).toString());
            temp.add(row.getCell(1).toString());
            temp.add(row.getCell(2).toString());
            int i1=Integer.valueOf(row.getCell(3).toString().substring(0, row.getCell(3).toString().length()-2));
            temp.add(String.valueOf(i1));
            int i2=Integer.valueOf(row.getCell(4).toString().substring(0, row.getCell(4).toString().length()-2));
            temp.add(String.valueOf(i2));
            int i3=Integer.valueOf(row.getCell(5).toString().substring(0, row.getCell(5).toString().length()-2));
            temp.add(String.valueOf(i3));
            temp.add("");
            map.put(i, temp);
        }
        File f = new File("C:\\Users\\SAMSUNG\\Desktop\\c.txt");
        FileWriter fos = new FileWriter(f, true);
        BufferedWriter fit = new BufferedWriter(fos);

        for (int i = 0; i < totalNoOfRows; i++) {
            String line1 = "INSERT INTO DRUGS";
            String line2 = "VALUES(";
            line2+=map.get(i).get(0)+",'";
            line2+=map.get(i).get(1)+"','";
            line2+=map.get(i).get(2)+"','";
            line2+=map.get(i).get(3)+"',";
            line2+=map.get(i).get(4).toString()+",";
            line2+=map.get(i).get(5).toString()+",";
            line2+=map.get(i).get(6).toString()+",'";
            line2+=map.get(i).get(7)+"');";
            fit.append(line1);
            fit.newLine();
            fit.append(line2);
            fit.newLine();
            fit.newLine();
        }
        fit.close();
        fos.close();
    }
}
