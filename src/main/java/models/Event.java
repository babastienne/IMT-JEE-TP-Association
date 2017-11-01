package models;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static database.Entity.ENTITY;


@Entity
public class Event {

    @Id
    private UID uid;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="date")
    private Date date;
    @Column(name="location")
    private String location;

    @OneToMany(mappedBy = "event")
    private List<Subscription> subscriptions;

    public Event(){

    }

    public Event(String title, String description, Date date, String location){
        this();
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.uid = new UID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<ServiceUser> getAllSubscribers(){
        List<ServiceUser> users = new ArrayList<>();
        //EntityManager em = ENTITY.getEntity();
        for(Subscription sub: this.subscriptions){
          //  users.add(em.find(ServiceUser.class, sub.get))
            users.add(sub.getUser());
        }

        return users;
    }


}
