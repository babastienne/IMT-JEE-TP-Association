package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ServiceOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private ServiceUser user;

    @OneToMany(mappedBy = "id")
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
