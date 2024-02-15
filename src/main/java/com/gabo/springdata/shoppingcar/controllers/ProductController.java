package com.gabo.springdata.shoppingcar.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.springdata.shoppingcar.entities.Product;
import com.gabo.springdata.shoppingcar.exceptions.NotFoundException;
import com.gabo.springdata.shoppingcar.repos.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Products")
public class ProductController {
	
	private ProductRepository repository;
	
	ProductController(ProductRepository repository){
		this.repository = repository;
	}
	
	//Insert product into table
	@PostMapping("/insert")
	public Product saveProduct(@Valid @RequestBody Product product) {
		List<Product> listProduct =  repository.findByName(product.getName());
		if(listProduct.isEmpty()) {
			return repository.save(product);
		}else {
			Product tempProduct =  listProduct.get(0);
			tempProduct.addOneToInventory();
			repository.save(tempProduct);
		}
		return product;
	}
	
	
	//Update an existing product
	@PostMapping("/update/{id}")
	public Product updateProductById( @PathVariable("id") Integer id,@RequestBody @Validated Product product) {
		Product tempProduct = repository.findById(id).get();
		if(tempProduct == null) {
			throw new NotFoundException("No product with id: "+ id);
		}else {
			tempProduct.setPrice(product.getPrice());
			tempProduct.setImage(product.getImage());
			tempProduct.setDescription(product.getDescription());
			tempProduct.setTotalProductsInventory(product.getTotalProductsInventory());
			return repository.save(tempProduct);
		}
		
	}
	
	
	
	//Delete existing product
	@PostMapping("/delete/{id}")
	public Product deleteProductById(@PathVariable("id") Integer id) {
		Product product = repository.findById(id).orElse(null);
		if(product == null) {
			throw new NotFoundException("No product with id: "+ id);
		}else {
			product.setStatus(false);
			return repository.save(product);
		}
	}
	

	
	//Retrieve list of existing products
	@GetMapping("/ProductsList")
	public List<String> getProducts() {
		List<String> names = new ArrayList<String>();
		List<Product> product = repository.findAll();
		for(Product i: product) {
			if(i.isStatus()==true) {
				names.add(i.getName());
			}
		}
		return names;
	}
	
	
	//Retrieve specific product filtered by name
	@GetMapping("/ProductsList/name/{name}")
	public List<Product> getProductsByName(@PathVariable("name") String name) {
		List<Product> product = repository.findByName(name);
		if(product.isEmpty()) {
			throw new NotFoundException("No product named: "+ name);
		}

		return product;
		
	}
	
	
	//Retrieve list of products filtered by price
	@GetMapping("/ProductsList/{minPrice}/{maxPrice}")
	public List<Product> getProductsByPriceBet(@PathVariable("minPrice") int minPrice, @PathVariable("maxPrice") int maxPrice) {
		List<Product> products = repository.findByPriceBetween(minPrice, maxPrice);
		if(products.isEmpty()) {
			throw new NotFoundException("No products with that range of prices: "+ minPrice + " - "+ maxPrice);
		}
		return products;
	}	
	
	
}
