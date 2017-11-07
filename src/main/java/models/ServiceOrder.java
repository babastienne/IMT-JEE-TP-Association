package models;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

/**
 * Classe modélisant une commande
 */
@Entity
public class ServiceOrder {

    /**
     * numéro de la commande
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    /**
     * Lien vers l'utilisateur qui a fait la commande
     */
    @OneToOne
    private ServiceUser user;

    /**
     * Liste d'ajout d'article
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true)
    private List<OrderLine> orderLines;

    public ServiceOrder(){}

    /**
     * Constructeur
     * @param user objet de l'utilisateur
     */
    public ServiceOrder(ServiceUser user){
        this.user = user;
    }

    public ServiceUser getUser() {
        return user;
    }

    public void setUser(ServiceUser user) {
        this.user = user;
    }

    /**
     * Récupére la liste des ajouts d'article dans la commande
     * @return liste d'ajout d'article
     */
    public List<OrderLine> getOrders(){
        return this.orderLines;
    }

    /**
     * Récupére l'ensemble des articles commandés ainsi que leur quantité
     * @return dictionnaire article quantité
     */
    public HashMap<Item, Integer> getItems(){
        HashMap<Item, Integer> itemsOrdered = new HashMap<>();
        for(OrderLine ol : this.orderLines){
            itemsOrdered.put(ol.getItem(), ol.getQuantityItem());
        }
        return itemsOrdered;
    }



}
