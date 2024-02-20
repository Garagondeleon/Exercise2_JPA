package com.gabo.springdata.shoppingcar.controllers;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabo.springdata.shoppingcar.entities.Product;
import com.gabo.springdata.shoppingcar.entities.User;
import com.gabo.springdata.shoppingcar.entities.Wishlist;
import com.gabo.springdata.shoppingcar.exceptions.NotFoundException;
import com.gabo.springdata.shoppingcar.repos.ProductRepository;
import com.gabo.springdata.shoppingcar.repos.UserRepository;
import com.gabo.springdata.shoppingcar.repos.WishlistRepository;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	private WishlistRepository wishRepository;
	private ProductRepository productRepository;
	private UserRepository userRepository;

	public WishlistController(WishlistRepository wishRepository, ProductRepository productRepository,
			UserRepository userRepository) {
		this.wishRepository = wishRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	// Insert product to wishlist
	@PostMapping("/addToWishlist/{id}/{name}")
	public Wishlist addProductToWishlist(@PathVariable("id") int id, @PathVariable("name") String name) {
		User user = userRepository.findById(id).orElse(null);
		List<Product> product = productRepository.findByName(name);
		if(user == null ) {
			throw new NotFoundException("No user with id: "+ id);
		}else if(product.isEmpty()){
			throw new NotFoundException("No product wiht name: "+ name);
		}else {
			Wishlist wishlist = new Wishlist();
			wishlist.setUser(user);
			wishlist.setProduct(product.get(0));
			return wishRepository.save(wishlist);
		}
		
	}
	
	
	// Delete existing product
		@DeleteMapping("/deleteFromWishlist/{userId}/{productId}")
		public void deleteProductFromWishlistById(@PathVariable("userId") int userId, @PathVariable("productId") int productId ) {
			User user = userRepository.findById(userId).orElse(null);
			Product product = productRepository.findById(productId).orElse(null);
			if(user == null ) {
				throw new NotFoundException("No user with id: "+ userId);
			}else if(product == null){
				throw new NotFoundException("No product wiht id: "+ productId);
			}else {
				Wishlist wishlist = wishRepository.findByUserAndProduct(user,product);
				if(wishlist != null) {
					wishRepository.delete(wishlist);
				}
			}
		}
	
	
	// Delete the whole wishlist from a user
		@Transactional
		@DeleteMapping("/deleteFromWishlist/{id}")
		public List<Wishlist> deleteWishlistById(@PathVariable("id") int id) {
			User user = userRepository.findById(id).orElse(null);
			if(user == null ) {
				throw new NotFoundException("No user with id: "+ id);
			}else {
				List<Wishlist> wishlist = wishRepository.deleteByUser(user);
				return wishlist;
			}
			
		}
	
}

