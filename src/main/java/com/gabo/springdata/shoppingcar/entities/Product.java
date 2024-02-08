package com.gabo.springdata.shoppingcar.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="PRODUCTS")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRODUCT_ID")
	private int product_id;
	@Column(name="NAME")
	private String name;
	@Column(name="PRICE")
	private int price;
	@Lob
	@Column(name="IMAGE")
	private byte[] image;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="TOTAL_PRODUCTS_INVENTORY")
	private int total_products_inventory;
	@Column(name="STATUS")
	private boolean status;
	
	@OneToMany(mappedBy="product",cascade = CascadeType.ALL)
	private List<OrderHistory> order;

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotal_products_inventory() {
		return total_products_inventory;
	}

	public void setTotal_products_inventory(int total_products_inventory) {
		this.total_products_inventory = total_products_inventory;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public void addOneToInventory() {
		this.total_products_inventory = this.total_products_inventory+1;
	}

	public List<OrderHistory> getOrder() {
		return order;
	}

	public void setOrder(List<OrderHistory> order) {
		this.order = order;
	}
	
	
}

