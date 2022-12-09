package pwr.bsadowski.applications;

import pwr.bsadowski.model.Client;
import pwr.bsadowski.model.Producent;
import pwr.bsadowski.tools.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProducentApp {
    static void exceptionMethod(int testnum) throws MyException {
        if(testnum < 0) throw new MyException();
    }
    public static void main(String[] args) {
        System.out.println("Producent App");
        DataWriter writer = new DataWriter();
        String allToFileToShop = "";

        ArrayList<ArrayList<String>> producents = DataReader.readFile("Producents.txt");
        int counterProducentId = producents.size();
        if(producents.isEmpty()){
            counterProducentId = 1;
        }
        else {
            counterProducentId += Integer.parseInt(producents.get(0).get(0));
        }

        writer.writeFile("Producents.txt", String.valueOf(counterProducentId));

        Producent producent = new Producent(counterProducentId);

        System.out.println(producent.getProducentID()+" "+producent.getProducentName());


        while (true) {
            System.out.println("1. Zgłoszone reklamacje.");
            System.out.println("2. Zarządaj reklamacjami.");
            System.out.println("3. Wyślij możliwość odbioru.");

            Scanner scan = new Scanner(System.in);
            int producentOption = Integer.parseInt(scan.nextLine());

            ArrayList<ArrayList<String>> time = DataReader.readFile("Time.txt");


            List<Reclamation> lShopProducent = DataCreator.listOfReclamation("ShopProducent.txt");

            switch (producentOption) {
                case 1: {
                    for (Reclamation reclamation : lShopProducent) {
                        if(reclamation.getProducentId() == producent.getProducentID()) {
                            System.out.println(reclamation.getReclamationId() + " " + reclamation.getClientId() + " " + reclamation.getItemId() + " " +
                                    reclamation.getStatus() + " " + reclamation.getOpis() + " " + reclamation.getProducentId() + " " + reclamation.getDateReclamationStart() + " " +
                                    reclamation.getDateReclamationToWorker() + " " + reclamation.getDateReclamationToProducent() + " " +
                                    reclamation.getDateReclamationToTaken() + " " + reclamation.getDateReclamationEnd());
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("Wybierz id reklamacji.");
                    int reclamationIdChoice = Integer.parseInt(scan.nextLine());

                    try {
                        exceptionMethod(reclamationIdChoice);
                    } catch (MyException e) {
                        System.out.println("Brak podanego id reklamacji.");
                        break;
                    }
                    for (int i = 0; i < lShopProducent.size(); i++) {
                        if (lShopProducent.get(i).getReclamationId() == reclamationIdChoice && lShopProducent.get(i).getStatus().equals("In progress") && lShopProducent.get(i).getProducentId() == producent.getProducentID()) {
                            System.out.println("1.Approval reclamation");
                            System.out.println("2.Disapproval reclamation");
                            int dateNow = Integer.parseInt(time.get(0).get(0));
                            int dateToGive = dateNow + 10;
                            int choseApproval = Integer.parseInt(scan.nextLine());
                            switch (choseApproval) {
                                case 1: {
                                    lShopProducent.get(i).setStatus("Approval");
                                    lShopProducent.get(i).setDateReclamationToProducent(dateNow);
                                    lShopProducent.get(i).setDateReclamationToTaken(dateToGive);


                                    allToFileToShop = CreateString.getString(allToFileToShop, lShopProducent);
                                    break;
                                }
                                case 2: {
                                    lShopProducent.get(i).setStatus("Disapproval");
                                    lShopProducent.get(i).setDateReclamationToProducent(dateNow);
                                    lShopProducent.get(i).setDateReclamationToTaken(dateToGive);

                                    allToFileToShop = CreateString.getString(allToFileToShop, lShopProducent);
                                    break;
                                }
                                default: {
                                    System.out.println("Wybrałeś złą opcje.");
                                }
                            }

                            writer.writeFile("ShopProducent.txt", allToFileToShop);
                            allToFileToShop = "";

                        }
                    }
                break;
                }
                case 3: {
                    for (Reclamation reclamation : lShopProducent) {
                        if (reclamation.getDateReclamationToTaken() <= Integer.parseInt(time.get(0).get(0)) && (reclamation.getStatus().equals("Approval") || reclamation.getStatus().equals("Disapproval")) && reclamation.getProducentId() == producent.getProducentID()) {
                            reclamation.setStatus("OrderToTake");
                        }
                    }
                    allToFileToShop = CreateString.getString(allToFileToShop, lShopProducent);

                    writer.writeFile("ShopProducent.txt", allToFileToShop);
                    allToFileToShop = "";

                    break;
                }
                default: {
                    System.out.println("Wybrałeś złą opcje.");
                }
            }
        }
    }
}



