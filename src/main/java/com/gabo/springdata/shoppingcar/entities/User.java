package com.gabo.springdata.shoppingcar.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private int user_id;
	@Column(name="NAME")
	private String name;
	@Column(name="LAST_NAME")
	private String last_name;
	@Column(name="BIO")
	private String bio;
	@Column(name="EMAIL")
	private String email;
	@Column(name="AREA_OF_INTEREST")
	private String area_of_interest;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<OrderHistory> order;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public String getArea_of_interest() {
		return area_of_interest;
	}

	public void setArea_of_interest(String area_of_interest) {
		this.area_of_interest = area_of_interest;
	}

	public List<OrderHistory> getOrder() {
		return order;
	}

	public void setOrder(List<OrderHistory> order) {
		this.order = order;
	}

}