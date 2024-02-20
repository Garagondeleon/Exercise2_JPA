package com.gabo.springdata.shoppingcar.entities;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="PRODUCTS")
public class Product {
	
	@Id
	@GenericGenerator(name="PRODUCT_ID", strategy = "com.gabo.springdata.shoppingcar.IDGenerator.CustomIdGenerator")
	@GeneratedValue(generator="PRODUCT_ID")
	private int productId;
	@Column(name="NAME")
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@Min(1)
	private int price;
	@Lob
	@Column(name="IMAGE")
	private byte[] image;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="TOTAL_PRODUCTS_INVENTORY")
	@NotNull
	private int totalProductsInventory;
	@Column(name="STATUS")
	private boolean status;
	
	@OneToMany(mappedBy="product",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrderHistory> order;
	
	@OneToMany(mappedBy="product",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Wishlist> wishlist;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public int getTotalProductsInventory() {
		return totalProductsInventory;
	}

	public void setTotalProductsInventory(int totalProductsInventory) {
		this.totalProductsInventory = totalProductsInventory;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public void addOneToInventory() {
		this.totalProductsInventory = this.totalProductsInventory+1;
	}
	public void subtractOneToInventory() {
		this.totalProductsInventory = this.totalProductsInventory-1;
	}

	public List<OrderHistory> getOrder() {
		return order;
	}

	public void setOrder(List<OrderHistory> order) {
		this.order = order;
	}

	public List<Wishlist> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<Wishlist> wishlist) {
		this.wishlist = wishlist;
	}
	
	
}

