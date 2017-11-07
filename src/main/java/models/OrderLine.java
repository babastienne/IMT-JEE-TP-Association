package models;

import javax.persistence.*;

/**
 * Classe permettant de faire la jointure OneToMany entre ServiceOrder et Item
 */
@Entity
public class OrderLine {

    /**
     * identifiant de la orderline
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderLineId;

    /**
     * Lien vers l'article concerné
     */
    @OneToOne
    private Item item;

    /**
     * Lien vers la commande concerné
     */
    @ManyToOne
    private ServiceOrder order;

    /**
     * Quantité commandé
     */
    private int quantityItem;

    public OrderLine(){}

    /**
     * Constructeur
     * @param item objet de l'article
     * @param order objet de la commande
     * @param p_quantityItem quantité commandé
     */
    public OrderLine(Item item, ServiceOrder order, int p_quantityItem){
        this.item = item;
        this.order = order;
        this.quantityItem = p_quantityItem;
    }

    public Item getItem(){
        return this.item;
    }

    public ServiceOrder getOrder(){
        return this.order;
    }

    public int getQuantityItem(){
        return this.quantityItem;
    }

    public void setQuantityItem(int quantityItem){
        this.quantityItem = quantityItem;
    }


}
