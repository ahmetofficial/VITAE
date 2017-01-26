package excelleditor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcellEditor_DrugFormsAdd {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //In these code part types of drugs has added to xls file as a different column
        InputStream fis = new FileInputStream("");
        POIFSFileSystem poiFs = new POIFSFileSystem(fis);
        Workbook wb = new HSSFWorkbook(poiFs);
        int totalNoOfSheets = wb.getNumberOfSheets();
        Sheet sheet = wb.getSheetAt(0);
        int totalNoOfRows = sheet.getPhysicalNumberOfRows();
        
        int amount = 0;
        for (int i = 0; i < totalNoOfRows; i++) {
            Row row = sheet.getRow(i);
            //System.out.println(row.getCell(0)+ " | "+row.getCell(1));
            if (
                row.getCell(0).toString().toUpperCase().contains("MEDICATED PLASTER")
//                 &&
//                (
//                 !row.getCell(1).toString().toLowerCase().equals("vial")
//                 &&
//                 !row.getCell(1).toString().toLowerCase().equals("ampoule")
//                 &&
//                 !row.getCell(1).toString().toLowerCase().equals("sachet")
//                )
            ) 
            {
                Cell c = row.createCell(2);
                //row.getCell(2).setCellValue("Solüsyon"); 
                //solution,infusion,ınfusion,injection,ınjection,gargle,gargara,lavman
                //row.getCell(2).setCellValue("Süspansiyon"); //suspension
                //row.getCell(2).setCellValue("Ampül"); //ampoule
                //row.getCell(2).setCellValue("Flakon"); //vial,şişe
                //row.getCell(2).setCellValue("Krem"); //cream
                //row.getCell(2).setCellValue("Merhem"); //ointment, gel
                //row.getCell(2).setCellValue("Kapsül"); //capsule,mikropellet
                //row.getCell(2).setCellValue("Şurup"); //syrup
                //row.getCell(2).setCellValue("Damla"); //drop
                //row.getCell(2).setCellValue("Tablet"); //tablet,lozenge,draje
                //row.getCell(2).setCellValue("Sprey"); //spray
                //row.getCell(2).setCellValue("Granül"); //granule
                //row.getCell(2).setCellValue("Şampuan"); //shampoo
                //row.getCell(2).setCellValue("Emülsiyon"); //emulsion
                //row.getCell(2).setCellValue("Supozituar (fitil)"); //suppository
                //row.getCell(2).setCellValue("Losyon"); //lotion,cutaneous liquid
                //row.getCell(2).setCellValue("Toz"); //powder
                //row.getCell(2).setCellValue("Efervesan tablet"); //effervescent tablet
                //row.getCell(2).setCellValue("İmplant Sistem"); //ımplant
                //row.getCell(2).setCellValue("İnhaler sistem"); //inhalation,inhalation vapour
                //row.getCell(2).setCellValue("Vajinal suppozituar"); //pessary
                //row.getCell(2).setCellValue("Paket"); //sachet
                //row.getCell(2).setCellValue("Diğer"); //iğne ucu,strip,pvc,ölçüm çubuğu,ölçüm cihazı,penfill,test şeridi
                row.getCell(2).setCellValue("Transdermal sistem"); 
                //strip,transdermal,flaster,transdermal patch,transdermal flaster,medicated plaster,plaster
                amount++;
            }
        }

//        fis.close();
//        FileOutputStream fos = new FileOutputStream("");
//        wb.write(fos);
//        fos.close();
//        System.out.println(amount + " kadar eleman oluşturuldu.");
    }
}
