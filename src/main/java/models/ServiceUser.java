package models;

import models.Authentification.AuthUser;

import static database.Entity.ENTITY;


import javax.persistence.*;


/**
 * Classe modélisant l'utilisateur
 */
@Entity
public class ServiceUser {

    /**
     * numéro d'utilisateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * prénom
     */
    @Column(name="firstName", nullable=false)
    private String firstName;

    /**
     * nom
     */
    @Column(name="lastName", nullable=false)
    private String lastName;

    /**
     * adresse email
     */
    @Column(name="identifiant", nullable=false, unique=true)
    private String identifiant;

    /**
     * adresse postale
     */
    @Column(name="adress", nullable=true)
    private String address;

    /**
     * code postal
     */
    @Column(name="zip", nullable=true)
    private int zip;

    /**
     * ville
     */
    @Column(name="city", nullable=true)
    private String city;

    /**
     * Lien vers la classe gérant l'authentification de l'utilisateur
     */
    @OneToOne(mappedBy = "serviceUser", cascade = {CascadeType.ALL})
    private AuthUser authUser;

    /**
     * Dernière commande lié à l'utilisateur
     */
    @OneToOne
    private ServiceOrder order;

    public ServiceUser(){}

    /**
     * Constructeur
     * @param firstname
     * @param lastname
     * @param identifiant
     * @param address
     * @param zip
     * @param city
     */
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