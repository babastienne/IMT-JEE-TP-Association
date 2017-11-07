package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static database.Entity.ENTITY;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="code", nullable=false)
    private long itemId;
    
    @Column(name="price", nullable=false)
    private double price;
    
    @Column(name="stock", nullable=false)
    private int stock;
    
    @Column(name="name")
    private String name;


    public Item(){}

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
