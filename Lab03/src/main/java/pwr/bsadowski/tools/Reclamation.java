package pwr.bsadowski.tools;

public class Reclamation {
    private int reclamationId;
    private int clientId;
    private int itemId;
    private String status;
    private String opis;
    private int dateReclamationStart;
    private int dateReclamationToWorker;
    private int dateReclamationToProducent;
    private int dateReclamationToTaken;
    private int dateReclamationEnd;
    private int producentId;

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setReclamationId(int reclamationId) {
        this.reclamationId = reclamationId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProducentId() {
        return producentId;
    }

    public void setProducentId(int producentId) {
        this.producentId = producentId;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getDateReclamationStart() {
        return dateReclamationStart;
    }

    public void setDateReclamationStart(int dateReclamationStart) {
        this.dateReclamationStart = dateReclamationStart;
    }

    public int getDateReclamationToWorker() {
        return dateReclamationToWorker;
    }

    public void setDateReclamationToWorker(int dateReclamationToWorker) {
        this.dateReclamationToWorker = dateReclamationToWorker;
    }

    public int getDateReclamationToProducent() {
        return dateReclamationToProducent;
    }

    public void setDateReclamationToProducent(int dateReclamationToProducent) {
        this.dateReclamationToProducent = dateReclamationToProducent;
    }

    public void setDateReclamationToTaken(int dateReclamationToTaken) {
        this.dateReclamationToTaken = dateReclamationToTaken;
    }

    public void setDateReclamationEnd(int dateReclamationEnd) {
        this.dateReclamationEnd = dateReclamationEnd;
    }

    public int getDateReclamationToTaken() {
        return dateReclamationToTaken;
    }


    public int getDateReclamationEnd() {
        return dateReclamationEnd;
    }

    public int getReclamationId() {
        return reclamationId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getStatus() {
        return status;
    }

    public String getOpis() {
        return opis;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Reclamation(int clientId, int reclamationIdCounter) {
        this.reclamationId = reclamationIdCounter;
        this.clientId = clientId;
        this.status = "Reclamation started";}

    public Reclamation(String reclamationId, String clientId, String itemId, String status, String opis, String producentId, String dateReclamationStart, String dateReclamationToWorker, String dateReclamationToProducent, String dateReclamationToTaken, String dateReclamationEnd) {
        this.reclamationId = Integer.parseInt(reclamationId);
        this.clientId = Integer.parseInt(clientId);
        this.itemId = Integer.parseInt(itemId);
        this.status = status;
        this.opis = opis;
        this.producentId = Integer.parseInt(producentId);
        this.dateReclamationStart = Integer.parseInt(dateReclamationStart);
        this.dateReclamationToWorker = Integer.parseInt(dateReclamationToWorker);
        this.dateReclamationToProducent = Integer.parseInt(dateReclamationToProducent);
        this.dateReclamationToTaken = Integer.parseInt(dateReclamationToTaken);
        this.dateReclamationEnd = Integer.parseInt(dateReclamationEnd);
    }
}
