package models;

import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity
public class ServiceOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private ServiceUser user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderLine> orderLines;


    public ServiceOrder(){}

    public ServiceOrder(ServiceUser user, Date date){
        this.user = user;
    }

    public ServiceUser getUser() {
        return user;
    }

    public void setUser(ServiceUser user) {
        this.user = user;
    }

    public void addItem(Item item, int quantity){
        OrderLine orderLine = new OrderLine(item, this, quantity);
        this.orderLines.add(orderLine);
    }

    public List<OrderLine> getOrders(){
        return this.orderLines;
    }
    public HashMap<Item, Integer> getItems(){
        HashMap<Item, Integer> itemsOrdered = new HashMap<>();
        for(OrderLine ol : this.orderLines){
            itemsOrdered.put(ol.getItem(), ol.getQuantityItem());
        }
        return itemsOrdered;
    }

   // public List<Item> getItems(){
   //     return this.orderLines;
   // }


}
