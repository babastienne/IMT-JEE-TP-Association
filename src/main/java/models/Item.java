package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name="price", nullable=false)
    private double price;
    
    @Column(name="stock", nullable=false)
    private int stock;
    
    @Column(name="name", nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private ServiceOrder order;

    public Item(){}

	public Item(double price, int stock, String name) {
		super();
		this.price = price;
		this.stock = stock;
		this.name = name;
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
