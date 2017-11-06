package models;

public class OrderLine {

    private long idItem;
    private String nameItem;
    private float priceItem;
    private int quantityItem;


    public OrderLine(long p_idItem, String p_nameItem, float p_priceItem, int p_quantityItem){
        this.idItem = p_idItem;
        this.nameItem = p_nameItem;
        this.priceItem = p_priceItem;
        this.quantityItem = p_quantityItem;
    }

    public long getIdItem() {
        return idItem;
    }


    public String getNameItem() {
        return nameItem;
    }

    public float getPriceItem() {
        return priceItem;
    }
    public int getQuantityItem() {
        return quantityItem;
    }

}
