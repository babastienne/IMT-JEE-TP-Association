package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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


}
