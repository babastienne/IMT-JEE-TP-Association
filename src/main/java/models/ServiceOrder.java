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

    private Date date;

    @OneToMany(mappedBy = "id")
    private List<Item> items;


    public ServiceOrder(){}

    public ServiceOrder(ServiceUser user, Date date){
        this.user = user;
        this.date = date;
    }

    public ServiceUser getUser() {
        return user;
    }

    public void setUser(ServiceUser user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public List<Item> getItems(){
        return this.items;
    }


}
