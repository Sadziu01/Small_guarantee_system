package pwr.bsadowski.applications;

import pwr.bsadowski.tools.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PracownikApp {

    static void exceptionMethod(int testnum) throws MyException{
        if(testnum < 0) throw new MyException();
    }

    public static void main(String[] args) {
        System.out.println("Worker App");
        String allToFileToProducent = "";
        String allToFileToClient = "";

        while(true){
            System.out.println("1. Zgłoszone reklamacje.");
            System.out.println("2. Przyjmowanie reklamacji.");
            System.out.println("3. Status reklamacji od producenta");
            System.out.println("4. Przekaż informacje od producenta do klienta.");


            Scanner scan = new Scanner(System.in);
            int workerOption = Integer.parseInt(scan.nextLine());

            ArrayList<ArrayList<String>> time = DataReader.readFile("Time.txt");

            List<Reclamation> lClientShop = DataCreator.listOfReclamation("ClientShop.txt");

            List<Reclamation> lShopProducent = DataCreator.listOfReclamation("ShopProducent.txt");

            switch (workerOption){
                case 1:{
                    for (Reclamation reclamation : lClientShop) {
                        System.out.println(reclamation.getReclamationId() + " " + reclamation.getClientId() + " " + reclamation.getItemId() + " " +
                                reclamation.getStatus() + " " + reclamation.getOpis() + " " + reclamation.getProducentId() + " " + reclamation.getDateReclamationStart() + " " +
                                reclamation.getDateReclamationToWorker() + " " + reclamation.getDateReclamationToProducent() + " " +
                                reclamation.getDateReclamationToTaken() + " " + reclamation.getDateReclamationEnd());
                    }
                    break;
                }
                case 2:{
                    System.out.println("Wybierz id reklamacji do przyjęcia.");
                    int reclamationIdChoice = Integer.parseInt(scan.nextLine());

                    try{
                        exceptionMethod(reclamationIdChoice);
                    } catch (MyException e) {
                        System.out.println("Brak podanego id reklamacji.");
                        break;
                    }
                    for (int i = 0; i < lClientShop.size(); i++) {
                        if (lClientShop.get(i).getReclamationId() == reclamationIdChoice && lClientShop.get(i).getStatus().equals("Reclamation started")) {
                            lClientShop.get(i).setStatus("In progress");
                            lClientShop.get(i).setDateReclamationToWorker(Integer.parseInt(time.get(0).get(0)));
                            System.out.println("Podaj id producenta");
                            int producentIdChoice = Integer.parseInt(scan.nextLine());

                            try{
                                exceptionMethod(producentIdChoice);
                            } catch (MyException e) {
                                System.out.println("Brak podanego id reklamacji.");
                                break;
                            }
                            lClientShop.get(i).setProducentId(producentIdChoice);

                            for (Reclamation reclamation : lClientShop) {
                                String toFileToClient = reclamation.getReclamationId() + ";" + reclamation.getClientId() + ";" + reclamation.getItemId() + ";" +
                                        reclamation.getStatus() + ";" + reclamation.getOpis() + ";" + reclamation.getProducentId() + ";" +  reclamation.getDateReclamationStart() + ";" +
                                        reclamation.getDateReclamationToWorker() + ";" + reclamation.getDateReclamationToProducent() + ";" +
                                        reclamation.getDateReclamationToTaken() + ";" + reclamation.getDateReclamationEnd() + "\n";
                                allToFileToClient = allToFileToClient + toFileToClient;

                                if (reclamation.getStatus().equals("In progress")) {
                                    String toFileToProducent = reclamation.getReclamationId() + ";" + reclamation.getClientId() + ";" + reclamation.getItemId() + ";" +
                                            reclamation.getStatus() + ";" + reclamation.getOpis() + ";" + reclamation.getProducentId() + ";" + reclamation.getDateReclamationStart() + ";" +
                                            reclamation.getDateReclamationToWorker() + ";" + reclamation.getDateReclamationToProducent() + ";" +
                                            reclamation.getDateReclamationToTaken() + ";" + reclamation.getDateReclamationEnd() + "\n";
                                    allToFileToProducent = allToFileToProducent + toFileToProducent;
                                }
                            }

                            DataWriter writer = new DataWriter();

                            writer.writeFile("ShopProducent.txt", allToFileToProducent);
                            System.out.println("Przekazano zgłoszenie dalej.");
                            allToFileToProducent = "";

                            writer.writeFile("ClientShop.txt", allToFileToClient);
                            System.out.println("Poinformowano klienta.");
                            allToFileToClient = "";
                        }
                    }
                    break;
                }
                case 3:{
                    for (Reclamation reclamation : lShopProducent) {
                        System.out.println(reclamation.getReclamationId() + " " + reclamation.getClientId() + " " + reclamation.getItemId() + " " +
                                reclamation.getStatus() + " " + reclamation.getOpis() + " " + reclamation.getProducentId() + " " + reclamation.getDateReclamationStart() + " " +
                                reclamation.getDateReclamationToWorker() + " " + reclamation.getDateReclamationToProducent() + " " +
                                reclamation.getDateReclamationToTaken() + " " + reclamation.getDateReclamationEnd());
                    }
                    break;
                }

                case 4:{
                    for (Reclamation reclamation : lShopProducent) {
                        for (Reclamation value : lClientShop) {
                            if (reclamation.getStatus().equals("Disapproval") || reclamation.getStatus().equals("Approval") || reclamation.getStatus().equals("OrderToTake")) {
                                if (reclamation.getReclamationId() == value.getReclamationId()) {
                                    value.setStatus(reclamation.getStatus());
                                    value.setOpis(reclamation.getOpis());
                                    value.setProducentId(reclamation.getProducentId());
                                    value.setDateReclamationStart(reclamation.getDateReclamationStart());
                                    value.setDateReclamationToWorker(reclamation.getDateReclamationToWorker());
                                    value.setDateReclamationToProducent(reclamation.getDateReclamationToProducent());
                                    value.setDateReclamationToTaken(reclamation.getDateReclamationToTaken());
                                }
                            }
                        }
                    }

                    allToFileToClient = CreateString.getString(allToFileToClient, lClientShop);

                    DataWriter writer = new DataWriter();

                    writer.writeFile("ClientShop.txt", allToFileToClient);
                    System.out.println("Poinformowano klienta.");
                    allToFileToClient = "";
                    break;
                }
                default:{
                    System.out.println("Wybrałeś złą opcje.");
                }
            }
        }
    }

}
