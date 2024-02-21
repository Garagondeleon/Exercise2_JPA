package com.gabo.springdata.shoppingcar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabo.springdata.shoppingcar.entities.Product;
import com.gabo.springdata.shoppingcar.entities.User;
import com.gabo.springdata.shoppingcar.entities.Wishlist;
import com.gabo.springdata.shoppingcar.exceptions.NotFoundException;
import com.gabo.springdata.shoppingcar.repos.ProductRepository;
import com.gabo.springdata.shoppingcar.repos.UserRepository;
import com.gabo.springdata.shoppingcar.repos.WishlistRepository;

@Service
public class WishlistService {
	
	@Autowired
	private WishlistRepository wishRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;


	// Insert product to wishlist
	public Wishlist addProductToWishlist(int id, String name) {
		User user = userRepository.findById(id).orElse(null);
		List<Product> product = productRepository.findByName(name);
		if (user == null) {
			throw new NotFoundException("No user with id: " + id);
		} else if (product.isEmpty()) {
			throw new NotFoundException("No product wiht name: " + name);
		} else {
			Wishlist wishlist = new Wishlist();
			wishlist.setUser(user);
			wishlist.setProduct(product.get(0));
			return wishRepository.save(wishlist);
		}

	}

	// Delete existing product
	public void deleteProductFromWishlistById(int userId,int productId) {
		User user = userRepository.findById(userId).orElse(null);
		Product product = productRepository.findById(productId).orElse(null);
		if (user == null) {
			throw new NotFoundException("No user with id: " + userId);
		} else if (product == null) {
			throw new NotFoundException("No product wiht id: " + productId);
		} else {
			Wishlist wishlist = wishRepository.findByUserAndProduct(user, product);
			if (wishlist != null) {
				wishRepository.delete(wishlist);
			}
		}
	}

	// Delete the whole wishlist from a user
	public List<Wishlist> deleteWishlistById(int id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new NotFoundException("No user with id: " + id);
		} else {
			List<Wishlist> wishlist = wishRepository.deleteByUser(user);
			return wishlist;
		}

	}

}
