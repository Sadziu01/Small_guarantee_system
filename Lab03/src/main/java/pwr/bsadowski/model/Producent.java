package pwr.bsadowski.model;

public class Producent {
    private int producentID;
    private String producentName;

    public int getProducentID() {
        return producentID;
    }

    public String getProducentName() {
        return producentName;
    }

    public Producent(int idCounter) {
        this.producentID = idCounter;
        this.producentName = "Producent"+idCounter;
    }
}

