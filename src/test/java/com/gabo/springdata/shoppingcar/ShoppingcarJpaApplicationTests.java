package com.gabo.springdata.shoppingcar;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.gabo.springdata.shoppingcar.entities.User;
import com.gabo.springdata.shoppingcar.repos.UserRepository;

@SpringBootTest
class ShoppingcarJpaApplicationTests {
	
	@Autowired
	UserRepository repository;
	
	@Test
	void testUser() {
		
	}

	@Test
	void testRead() {
		User user = repository.findById(1).get();
		System.out.println(user.getName());
		
	}
	
	@Test
	void testGetUsers() {
		List<User> user = repository.findAll();
		for(User i : user) {
			System.out.println(i); 
		}
	}

}
