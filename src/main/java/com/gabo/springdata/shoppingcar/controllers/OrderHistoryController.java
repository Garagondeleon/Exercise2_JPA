package com.gabo.springdata.shoppingcar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabo.springdata.shoppingcar.service.OrderHistoryService;

@RestController
@RequestMapping("/order")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;

	// Buy one or more products
	@PostMapping("/buyProduct/{id}/{products}")
	public void buyProducts(@PathVariable("id") int id, @PathVariable("products") String products) {
		orderHistoryService.buyProducts(id, products);
	}
}
