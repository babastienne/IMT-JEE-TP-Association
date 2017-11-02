package models;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

import static database.Entity.ENTITY;

@Entity
public class ServiceUser {

    @Id
    private UID uid;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;

//    @OneToMany(targetEntity = Order.class, mappedBy = "user")
//    List<Order> order;

    @OneToOne
    private ServiceOrder order;

    public ServiceUser(){}

    public ServiceUser(String firstname, String lastname){
        this();
        this.uid = new UID();
        this.firstname = firstname;
        this.lastname = lastname;

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public ServiceOrder getOrder(){
        return this.order;
    }

    public void removeOrder(){
        EntityManager em = ENTITY.getEntity();
        em.remove(this.order);
        em.getTransaction().commit();
        this.order = null;
    }

    public void addOrder(ServiceOrder order){
        this.order = order;
    }
}


