package pwr.bsadowski.model;

public class Client {
    private int clientID;
    private String clientName;

    public int getClientID() {
        return clientID;
    }

    public String getClientName() {
        return clientName;
    }



    public Client(int idCounter) {
        this.clientID = idCounter;
        this.clientName = "Client"+idCounter;
    }
}
