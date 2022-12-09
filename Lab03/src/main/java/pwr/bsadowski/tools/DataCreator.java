package pwr.bsadowski.tools;

import java.util.ArrayList;
import java.util.List;

public class DataCreator {
    public static List<Reclamation> listOfReclamation(String fileName){
        List<Reclamation> list = new ArrayList<>();
        ArrayList<ArrayList<String>> temporaryList;
        DataReader reader = new DataReader();
        temporaryList = reader.readFile(fileName);

        for (ArrayList<String> strings : temporaryList) {

            list.add(new Reclamation(strings.get(0), strings.get(1), strings.get(2),strings.get(3), strings.get(4), strings.get(5), strings.get(6),
                    strings.get(7), strings.get(8), strings.get(9), strings.get(10)));
        }
        return list;
    }
}
