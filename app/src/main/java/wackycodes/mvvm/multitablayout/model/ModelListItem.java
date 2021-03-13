package wackycodes.mvvm.multitablayout.model;

public class ModelListItem {

    private String itemName;
    private int itemID;

    public ModelListItem() {
    }

    public ModelListItem(String itemName, int itemID) {
        this.itemName = itemName;
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
