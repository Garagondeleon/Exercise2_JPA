package com.gabo.springdata.shoppingcar.entities;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="USERS")
public class User {

	@Id
	@GenericGenerator(name="USER_ID", strategy = "com.gabo.springdata.shoppingcar.IDGenerator.CustomIdGenerator")
	@GeneratedValue(generator="USER_ID")
	private int userId;
	@NotNull
	@NotEmpty
	@Column(name="NAME")
	private String name;
	@NotNull
	@NotEmpty
	@Column(name="LAST_NAME")
	private String lastName;
	@Column(name="BIO")
	private String bio;
	@NotNull
	@NotEmpty
	@Column(name="EMAIL")
	private String email;
	@Column(name="AREA_OF_INTEREST")
	private String areaOfInterest;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrderHistory> order;

	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Wishlist> wishlist;
	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAreaOfInterest() {
		return areaOfInterest;
	}

	public void setAreaOfInterest(String areaOfInterest) {
		this.areaOfInterest = areaOfInterest;
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