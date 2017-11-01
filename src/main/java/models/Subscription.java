package models;


import javax.persistence.*;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private ServiceUser user;

    private Event event;


    public Subscription(){}

    public Subscription(ServiceUser user, Event event){
        this();
        this.user = user;
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public ServiceUser getUser() {
        return user;
    }

    public void setUser(ServiceUser user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
