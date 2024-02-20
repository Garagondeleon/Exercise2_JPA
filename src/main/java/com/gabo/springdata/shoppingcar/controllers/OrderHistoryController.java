package com.gabo.springdata.shoppingcar.controllers;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabo.springdata.shoppingcar.entities.OrderHistory;
import com.gabo.springdata.shoppingcar.entities.Product;
import com.gabo.springdata.shoppingcar.entities.User;
import com.gabo.springdata.shoppingcar.exceptions.NotFoundException;
import com.gabo.springdata.shoppingcar.repos.OrderHistoryRepository;
import com.gabo.springdata.shoppingcar.repos.ProductRepository;
import com.gabo.springdata.shoppingcar.repos.UserRepository;


@RestController
@RequestMapping("/order")
public class OrderHistoryController {

	private OrderHistoryRepository orderRepository;
	private ProductRepository productRepository;
	private UserRepository userRepository;

	public OrderHistoryController(OrderHistoryRepository orderRepository, ProductRepository productRepository,
			UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	//Buy one or more products
	@PostMapping("/buyProduct/{id}/{products}")
	public void buyProducts(@PathVariable("id") int id,@PathVariable("products") String products) {
		User user = userRepository.findById(id).orElse(null);
		String[] productslist = products.split(",");
		if(user == null) {
			throw new NotFoundException("No user with id: "+ id);
		}
		else if(productslist.length == 0) {
			throw new NotFoundException("No products with names: "+ products);
		}else {
			List<Product> listOfProducts = productRepository.findAll();
			List<Product> filteredList;
			List<String> productsFilter = Arrays.asList(productslist);
			
			filteredList = listOfProducts	
			.stream()
			.filter(e -> productsFilter.contains(e.getName()))
			.collect(Collectors.toList());
			
			for(int i=0; i<filteredList.size(); i++) {
				OrderHistory order = new OrderHistory();
				order.setUser(user);
				Timestamp date = new Timestamp(System.currentTimeMillis());
				order.setOrderDate(date);
				Product currentProduct =filteredList.get(i);
				order.setProduct(currentProduct);
				if(order.checkInventory(currentProduct)==null) {
					throw new NotFoundException("There is no more inventory for:" + currentProduct.getName());
				}else {
					orderRepository.save(order);
				}
				
			}
			
		}
		
		
	}

}
