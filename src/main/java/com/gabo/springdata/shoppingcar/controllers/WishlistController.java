package com.gabo.springdata.shoppingcar.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabo.springdata.shoppingcar.entities.Wishlist;
import com.gabo.springdata.shoppingcar.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;

	// Insert product to wishlist
	@PostMapping("/addToWishlist/{id}/{name}")
	public Wishlist addProductToWishlist(@PathVariable("id") int id, @PathVariable("name") String name) {
		return wishlistService.addProductToWishlist(id, name);
	}
	
	
	// Delete existing product
		@DeleteMapping("/deleteFromWishlist/{userId}/{productId}")
		public void deleteProductFromWishlistById(@PathVariable("userId") int userId, @PathVariable("productId") int productId ) {
			wishlistService.deleteProductFromWishlistById(userId, productId);
		}
	
	
	// Delete the whole wishlist from a user
		@Transactional
		@DeleteMapping("/deleteFromWishlist/{id}")
		public List<Wishlist> deleteWishlistById(@PathVariable("id") int id) {
			return wishlistService.deleteWishlistById(id);
		}
	
}

