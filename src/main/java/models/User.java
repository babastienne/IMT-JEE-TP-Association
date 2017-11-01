package models;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

public class User {

    private UID uid;
    private String firstname;
    private String lastname;
    private List<Event> subscribedEvents;


    public User(String firstname, String lastname){
        this.uid = new UID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.subscribedEvents = new ArrayList<>();

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


    public void subscribeEvent(Event event){
        this.subscribedEvents.add(event);
    }

    public void unsubscribeEvent(Event event){
        this.subscribedEvents.remove(event);
    }

}
