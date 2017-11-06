package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ServiceOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    @OneToOne
    private ServiceUser user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "add_item", catalog = "associationdb", joinColumns = {
            @JoinColumn(name = "ORDER_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "ITEM_ID",
                    nullable = false, updatable = false) })
    private List<Item> items;


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

    public void addItem(Item item){
        this.items.add(item);
    }

    public List<Item> getItems(){
        return this.items;
    }


}
