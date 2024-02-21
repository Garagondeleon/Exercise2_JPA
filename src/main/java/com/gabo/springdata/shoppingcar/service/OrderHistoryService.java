package com.gabo.springdata.shoppingcar.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabo.springdata.shoppingcar.entities.OrderHistory;
import com.gabo.springdata.shoppingcar.entities.Product;
import com.gabo.springdata.shoppingcar.entities.User;
import com.gabo.springdata.shoppingcar.exceptions.NotFoundException;
import com.gabo.springdata.shoppingcar.repos.OrderHistoryRepository;
import com.gabo.springdata.shoppingcar.repos.ProductRepository;
import com.gabo.springdata.shoppingcar.repos.UserRepository;

@Service
public class OrderHistoryService {

	@Autowired
	private OrderHistoryRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;

	// Buy one or more products
	public void buyProducts(int id, String products) {
		User user = userRepository.findById(id).orElse(null);
		String[] productslist = products.split(",");
		if (user == null) {
			throw new NotFoundException("No user with id: " + id);
		} else if (productslist.length == 0) {
			throw new NotFoundException("No products with names: " + products);
		} else {
			List<Product> listOfProducts = productRepository.findAll();
			List<Product> filteredList;
			List<String> productsFilter = Arrays.asList(productslist);

			filteredList = listOfProducts.stream().filter(e -> productsFilter.contains(e.getName()))
					.collect(Collectors.toList());

			for (int i = 0; i < filteredList.size(); i++) {
				OrderHistory order = new OrderHistory();
				order.setUser(user);
				Timestamp date = new Timestamp(System.currentTimeMillis());
				order.setOrderDate(date);
				Product currentProduct = filteredList.get(i);
				order.setProduct(currentProduct);
				if (order.checkInventory(currentProduct) == null) {
					throw new NotFoundException("There is no more inventory for:" + currentProduct.getName());
				} else {
					orderRepository.save(order);
				}

			}

		}

	}

}
