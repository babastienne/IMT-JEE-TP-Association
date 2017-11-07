package models;

import models.Authentification.AuthUser;
import org.hibernate.annotations.GenericGenerator;

import static database.Entity.ENTITY;

import java.rmi.server.UID;

import javax.persistence.*;

@Entity
public class ServiceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="firstName", nullable=false)
    private String firstName;

    @Column(name="lastName", nullable=false)
    private String lastName;

    @Column(name="identifiant", nullable=false, unique=true)
    private String identifiant;

    @Column(name="adress", nullable=true)
    private String address;

    @Column(name="zip", nullable=true)
    private int zip;

    @Column(name="city", nullable=true)
    private String city;

//    @OneToMany(targetEntity = Order.class, mappedBy = "user")
//    List<Order> order;


    @OneToOne(mappedBy = "serviceUser", cascade = {CascadeType.ALL})
    private AuthUser authUser;

    @OneToOne
    private ServiceOrder order;

    public ServiceUser(){}

    public ServiceUser(String firstname, String lastname, String identifiant, String address, int zip, String city){
        this();
        this.firstName = firstname;
        this.lastName = lastname;
        //this.authUser = authUser;
        this.identifiant = identifiant;
        this.address = address;
        this.zip = zip;
        this.city = city;

    }

    public long getUid() {
        return id;
    }

    public void setUid(long id) {
        this.id = id;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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