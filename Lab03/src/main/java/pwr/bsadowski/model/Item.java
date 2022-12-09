package pwr.bsadowski.model;

public class Item {
    private int itemID;
    private String itemName;



    public int getItemID() {
        return itemID;
    }

    public Item(String itemName, int itemID) {
        this.itemID = itemID;
        this.itemName = itemName;
    }
}
