package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Classe modélisant un article
 */
@Entity
public class Item {

	/**
	 * Numéro de l'article
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="code", nullable=false)
    private long itemId;

	/**
	 * Prix de l'article
	 */
	@Column(name="price", nullable=false)
    private double price;

	/**
	 * Stock disponible de l'article
	 */
	@Column(name="stock", nullable=false)
    private int stock;

	/**
	 * Nom de l'article
	 */
	@Column(name="name")
    private String name;


    public Item(){}

	/**
	 * Constructeur
	 * @param price prix
	 * @param stock stock disponible
	 * @param name nom
	 */
	public Item(double price, int stock, String name) {
		super();
		this.price = price;
		this.stock = stock;
		this.name = name;
	}


	public long getId() {
		return this.itemId;
	}

	public void setId(long id) {
		this.itemId = id;
	}

	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
