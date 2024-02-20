package com.gabo.springdata.shoppingcar.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ORDER_HISTORY")
public class OrderHistory {
	
	@Id
	@GenericGenerator(name="ORDER_ID", strategy = "com.gabo.springdata.shoppingcar.IDGenerator.CustomIdGenerator")
	@GeneratedValue(generator="ORDER_ID")
	private int orderId;
	
	@Column(name="ORDER_DATE")
	private Timestamp orderDate;
	
	@ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private User user;
	
	@Column(name="USER_ID",insertable=false, updatable=false )
	private int userId;
	
	@ManyToOne(targetEntity = Product.class,fetch = FetchType.LAZY)
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	@Column(name="USER_ID",insertable=false, updatable=false)
	private int productId;
	

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String checkInventory(Product product) {
		if(this.getProduct().getTotalProductsInventory()==0) {
			return null;
		}else {	
			this.getProduct().subtractOneToInventory();
		}
		return "notNull";
	}
}
