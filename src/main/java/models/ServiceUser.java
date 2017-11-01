package models;

import javax.persistence.*;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ServiceUser {

    @Id
    private UID uid;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;

    @OneToMany(mappedBy = "user")
    List<Subscription> subscriptions;

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

    public List<Event> getAllSubscribedEvent(){
        List<Event> events = new ArrayList<>();
        for(Subscription sub: this.subscriptions){
            events.add(sub.getEvent());
        }
        return events;
    }

}
