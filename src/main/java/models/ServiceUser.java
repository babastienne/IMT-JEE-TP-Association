package models;

import static database.Entity.ENTITY;

import java.rmi.server.UID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ServiceUser")
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
    private String adress;
    
    @Column(name="zip", nullable=true)
    private int zip;
    
    @Column(name="city", nullable=true)
    private String city;
    
    @Column(name="country", nullable=true)
    private String country;

//    @OneToMany(targetEntity = Order.class, mappedBy = "user")
//    List<Order> order;

    @OneToOne(fetch=FetchType.EAGER)
    private ServiceOrder order;

    public ServiceUser(){}

    public ServiceUser(String firstname, String lastname){
        this();
        this.uid = new UID();
        this.firstName = firstname;
        this.lastName = lastname;
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
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
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


