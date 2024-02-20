package com.gabo.springdata.shoppingcar.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import com.gabo.springdata.shoppingcar.entities.Product;
import com.gabo.springdata.shoppingcar.entities.User;
import com.gabo.springdata.shoppingcar.entities.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer>, CrudRepository<Wishlist, Integer> {


	Wishlist findByUserAndProduct(User user, Product product);

	List<Wishlist> deleteByUser(User user);


	

	
}
