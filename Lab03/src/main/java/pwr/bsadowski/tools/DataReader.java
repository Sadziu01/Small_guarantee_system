package pwr.bsadowski.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataReader {

    public static ArrayList<ArrayList<String>> readFile(String name) {
        ArrayList<ArrayList<String>> TempList = new ArrayList<>();
        try {
            InputStream in = new FileInputStream(name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                TempList.add(new ArrayList<>());

//                line = line.replaceAll("\\s+", "");
                for(String number : line.split("[;,]")){
                    TempList.get(TempList.size()-1).add(number);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return TempList;
    }
}
