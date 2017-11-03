package models;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ServiceOrder")
public class ServiceOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private long id;

    @OneToOne(fetch=FetchType.EAGER)
    private ServiceUser user;

    @OneToMany(mappedBy="order")
    private Set<Item> items = new HashSet<Item>();


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

    public Collection<Item> getItems(){
        return this.items;
    }
    
    public void clearItems() {
    	this.items = new HashSet<Item>();
    }


}
