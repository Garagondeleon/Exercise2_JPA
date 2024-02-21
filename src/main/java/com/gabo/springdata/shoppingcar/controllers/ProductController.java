package com.gabo.springdata.shoppingcar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.springdata.shoppingcar.entities.Product;
import com.gabo.springdata.shoppingcar.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// Insert product into table
	@PostMapping("/insert")
	public Product saveProduct(@Valid @RequestBody Product product) {
		return productService.saveProduct(product);
	}

	// Update an existing product
	@PostMapping("/update/{id}")
	public Product updateProductById(@PathVariable("id") int id, @RequestBody @Validated Product product) {
		return productService.updateProductById(id, product);
	}

	// Delete existing product
	@PostMapping("/delete/{id}")
	public Product deleteProductById(@PathVariable("id") int id) {
		return productService.deleteProductById(id);
	}

	// Retrieve list of existing products
	@GetMapping("/ProductsList")
	public List<String> getProducts() {
		return productService.getProducts();
	}

	// Retrieve specific product filtered by name
	@GetMapping("/ProductsList/name/{name}")
	public List<Product> getProductsByName(@PathVariable("name") String name) {
		return productService.getProductsByName(name);
	}

	// Retrieve list of products filtered by price
	@GetMapping("/ProductsList/{minPrice}/{maxPrice}")
	public List<Product> getProductsByPriceBet(@PathVariable("minPrice") int minPrice,
			@PathVariable("maxPrice") int maxPrice) {
		return productService.getProductsByPriceBet(minPrice, maxPrice);
	}

}
