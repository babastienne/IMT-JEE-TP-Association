package models;

import models.Authentification.AuthUser;

import static database.Entity.ENTITY;

import java.rmi.server.UID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ServiceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private UID uid;
    
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

    @OneToOne
    private AuthUser authUser;

    @OneToOne
    private ServiceOrder order;

    public ServiceUser(){}

    public ServiceUser(String firstname, String lastname, String identifiant, String address, int zip, String city, AuthUser authUser){
        this();
        this.uid = new UID();
        this.firstName = firstname;
        this.lastName = lastname;
        this.authUser = authUser;
        this.identifiant = identifiant;
        this.address = address;
        this.zip = zip;
        this.city = city;

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
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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


