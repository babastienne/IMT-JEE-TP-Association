package models;

import javax.persistence.*;
import javax.persistence.criteria.Order;


@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderLineId;

    @OneToOne
    private Item item;

    @OneToOne
    private ServiceOrder order;

    //private long idItem;
    //private String nameItem;
    //private float priceItem;
    private int quantityItem;

    public OrderLine(){}

   // public OrderLine(long p_idItem, String p_nameItem, float p_priceItem, int p_quantityItem){
        //this.idItem = p_idItem;
        //this.nameItem = p_nameItem;
        //this.priceItem = p_priceItem;
    public OrderLine(Item item, ServiceOrder order, int p_quantityItem){
        this.item = item;
        this.order = order;
        this.quantityItem = p_quantityItem;
    }

    public Item getItem(){
        return this.item;
    }

    public ServiceOrder getOrder(){
        return this.order;
    }

    public int getQuantityItem(){
        return this.quantityItem;
    }

   // public long getIdItem() {
   //     return idItem;
    //}


   /* public String getNameItem() {
        return nameItem;
    }

    public float getPriceItem() {
        return priceItem;
    }
    public int getQuantityItem() {
        return quantityItem;
    }
*/
}
