package com.gabo.springdata.shoppingcar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabo.springdata.shoppingcar.entities.Product;
import com.gabo.springdata.shoppingcar.exceptions.NotFoundException;
import com.gabo.springdata.shoppingcar.repos.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	// Insert product into table
	public Product saveProduct(Product product) {
		List<Product> listProduct = repository.findByName(product.getName());
		if (listProduct.isEmpty()) {
			return repository.save(product);
		} else {
			Product tempProduct = listProduct.get(0);
			tempProduct.addOneToInventory();
			repository.save(tempProduct);
		}
		return product;
	}

	// Update an existing product
	public Product updateProductById(int id, Product product) {
		Product tempProduct = repository.findById(id).get();
		if (tempProduct == null) {
			throw new NotFoundException("No product with id: " + id);
		} else {
			tempProduct.setPrice(product.getPrice());
			tempProduct.setImage(product.getImage());
			tempProduct.setDescription(product.getDescription());
			tempProduct.setTotalProductsInventory(product.getTotalProductsInventory());
			return repository.save(tempProduct);
		}

	}

	// Delete existing product
	public Product deleteProductById(int id) {
		Product product = repository.findById(id).orElse(null);
		if (product == null) {
			throw new NotFoundException("No product with id: " + id);
		} else {
			product.setStatus(false);
			return repository.save(product);
		}
	}

	// Retrieve list of existing products
	public List<String> getProducts() {
		List<String> names = new ArrayList<String>();
		List<Product> product = repository.findAll();
		for (Product i : product) {
			if (i.isStatus() == true) {
				names.add(i.getName());
			}
		}
		return names;
	}

	// Retrieve specific product filtered by name
	public List<Product> getProductsByName(String name) {
		List<Product> product = repository.findByName(name);
		if (product.isEmpty()) {
			throw new NotFoundException("No product named: " + name);
		}

		return product;

	}

	// Retrieve list of products filtered by price
	public List<Product> getProductsByPriceBet(int minPrice, int maxPrice) {
		List<Product> products = repository.findByPriceBetween(minPrice, maxPrice);
		if (products.isEmpty()) {
			throw new NotFoundException("No products with that range of prices: " + minPrice + " - " + maxPrice);
		}
		return products;
	}

}
