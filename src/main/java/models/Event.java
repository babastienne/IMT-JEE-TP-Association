package models;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Date;


@Entity
public class Event {

    private String title;
    private String description;
    private Date date;
    private String location;
    private ArrayList<User> subscribed;
    private UID uid;

    public Event(String title, String description, Date date, String location, ArrayList subscribed){
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.subscribed = subscribed;
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

    public ArrayList<User> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(ArrayList<User> subscribed) {
        this.subscribed = subscribed;
    }


}
