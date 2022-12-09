package pwr.bsadowski.applications;

import pwr.bsadowski.model.Client;
import pwr.bsadowski.model.Item;
import pwr.bsadowski.tools.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KlientApp {
    public static void main(String[] args) {
        String allToFileToShop = "";
        System.out.println("ClientApp");

        ArrayList<ArrayList<String>> clients = DataReader.readFile("Clients.txt");
        int counterClientId = clients.size();
        if(clients.isEmpty()){
            counterClientId = 1;
        }
        else {
            counterClientId += Integer.parseInt(clients.get(0).get(0));
        }

        DataWriter writer = new DataWriter();
        writer.writeFile("Clients.txt", String.valueOf(counterClientId));


        Client klient = new Client(counterClientId);

        System.out.println(klient.getClientID()+" "+klient.getClientName());

        String allToFile = "";

        while (true) {
            System.out.println("1. Złóż reklamacje.");
            System.out.println("2. Zareklamowane przedmioty.");
            System.out.println("3. Odbierz przedmioty.");
            Scanner scan = new Scanner(System.in);
            int clientOption = Integer.parseInt(scan.nextLine());

            List<Reclamation> llClientShop = DataCreator.listOfReclamation("ClientShop.txt");

            ArrayList<ArrayList<String>> time = DataReader.readFile("Time.txt");

            switch (clientOption) {
                case 1: {
                    List<Reclamation> lClientShop = DataCreator.listOfReclamation("ClientShop.txt");
                    Reclamation reklamacja = new Reclamation(klient.getClientID(), lClientShop.size()+1);
                    System.out.println("Podaj nazwe przedmiotu do reklamacji:");
                    String itemName = scan.nextLine();

                    Item item = new Item(itemName, lClientShop.size()+1);
                    reklamacja.setItemId(item.getItemID());

                    System.out.println("Podaj opis reklamacji:");
                    String describe = scan.nextLine();
                    reklamacja.setOpis(describe);


                    reklamacja.setProducentId(0);
                    reklamacja.setDateReclamationStart(Integer.parseInt(time.get(0).get(0)));
                    reklamacja.setDateReclamationToWorker(0);
                    reklamacja.setDateReclamationToProducent(0);
                    reklamacja.setDateReclamationToTaken(0);
                    reklamacja.setDateReclamationEnd(0);

                    allToFile = CreateString.getString(allToFile, lClientShop);

                    String toFile = reklamacja.getReclamationId() + ";" + reklamacja.getClientId() + ";" + reklamacja.getItemId() + ";" +
                            reklamacja.getStatus() + ";" + reklamacja.getOpis() + ";" + reklamacja.getProducentId() + ";" +  reklamacja.getDateReclamationStart() + ";" +
                            reklamacja.getDateReclamationToWorker() + ";" + reklamacja.getDateReclamationToProducent() + ";" + reklamacja.getDateReclamationToTaken() + ";" +
                            reklamacja.getDateReclamationEnd() + "\n";

                    allToFile = allToFile + toFile;
                    writer.writeFile("ClientShop.txt", allToFile);
                    allToFile = "";
                    break;
                }

                case 2: {
                    for (Reclamation reclamation : llClientShop) {
                        if (reclamation.getClientId() == klient.getClientID()) {
                            System.out.println(reclamation.getReclamationId() + " " + reclamation.getStatus());
                        }
                    }
                    break;
                }
                case 3: {
                    for(int i = 0; i< llClientShop.size(); i++){
                        if(llClientShop.get(i).getStatus().equals("OrderToTake") && llClientShop.get(i).getClientId() == klient.getClientID()){
                            llClientShop.get(i).setStatus("Completed");
                            llClientShop.get(i).setDateReclamationEnd(Integer.parseInt(time.get(0).get(0)));
                        }

                        allToFileToShop = CreateString.getString(allToFileToShop, llClientShop);
                        writer.writeFile("ClientShop.txt", allToFileToShop);
                        allToFileToShop = "";
                    }
                    break;
                }
                default:{
                    System.out.println("Wybrałeś złą opcje.");
                }

            }
        }
    }
}
