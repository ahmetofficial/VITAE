package scriptbuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

public class DrugCategoryInsertScriptBuilder {

    public static void main(String argv[]) {
        TreeSet<String> generalTypeInsertScript=new TreeSet<>();
        ArrayList<String> detailedTypeInsertScript=new ArrayList<>();
        
        try {
            FileInputStream fis = new FileInputStream(new File("C:\\Users\\SAMSUNG\\Desktop\\İlaç Tipleri.txt"));
            InputStreamReader isr = new InputStreamReader((fis), "ISO-8859-9");
            BufferedReader br = new BufferedReader(isr);
            String line;
            String previousGroup=null;
            while ((line = br.readLine()) != null) {
                String firstLetter=line.substring(0, 1);;
                String gGroup = null;
                switch(firstLetter){
                    case "A": gGroup = "Sindirim sistemi ve Metabolizma"; break;
                    case "B": gGroup = "Kan ve Kan yapıcı organlar"; break;
                    case "C": gGroup = "Kardiyovasküler sistem"; break;
                    case "D": gGroup = "Dermatolojik ilaçlar"; break;
                    case "G": gGroup = "Genitoüriner sistem ve seks hormonları"; break;
                    case "H": gGroup = "Sex hormonları hariç sistemik hormonal ürünler"; break;
                    case "J": gGroup = "Sistemik kullanım için genel antiinfektifler"; break;
                    case "L": gGroup = "Antineoplastik ve İmmünomodülatör ajanlar"; break;
                    case "M": gGroup = "Kas-İskelet Sistemi"; break;
                    case "N": gGroup = "Sinir Sistemi"; break;
                    case "P": gGroup = "Antiparazitik ürünler"; break;
                    case "R": gGroup = "Solunum Sistemi"; break;
                    case "S": gGroup = "Duyu Organları"; break;
                    case "V": gGroup = "Diğer"; break;
                }
                String dGroup= line.substring(0,3);
                String dSentence = line.substring(6);
                
                generalTypeInsertScript.add("INSERT INTO GENERAL_TYPE_GROUPS "+"VALUES('"+firstLetter+"','"+gGroup+"');");
                detailedTypeInsertScript.add("INSERT INTO TYPES_OF_DRUGS "+"VALUES('"+dGroup+"','"+dSentence+"','"+firstLetter+"');");
            }
            
            File f=new File("C:\\Users\\SAMSUNG\\Desktop\\a.txt");
            FileWriter fos = new FileWriter(f,true);
            BufferedWriter fit=new BufferedWriter(fos);
            
            ArrayList<String> temp=new ArrayList<>();
            temp.addAll(generalTypeInsertScript);
            for (int i = 0; i < detailedTypeInsertScript.size(); i++) {
                line=detailedTypeInsertScript.get(i);
                fit.append(line);
                fit.newLine();
            }
            fit.close();
         
        } catch (IOException e) {
        }
    }

}
