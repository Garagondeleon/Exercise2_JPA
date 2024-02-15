package com.gabo.springdata.shoppingcar.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gabo.springdata.shoppingcar.entities.User;
import com.gabo.springdata.shoppingcar.exceptions.NotFoundException;
import com.gabo.springdata.shoppingcar.repos.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Users")
public class UserController {
	
	private UserRepository repository;
	
	UserController(UserRepository repository){
		this.repository = repository;
	}
	

	//Insert user into table
	@PostMapping("/insert")
	public User insertUser(@Valid @RequestBody User user) {
		List<User> tempUser = repository.findByEmail(user.getName()); 
		if(tempUser.isEmpty()) {
			return repository.save(user);
		}
		return user;
	}
	
	
	//Update an existing user
	@PostMapping("/update/{id}")
	public User updateUserById( @PathVariable("id") Integer id,@Validated @RequestBody User user) {
		User tempUser = repository.findById(id).get();
		if(tempUser == null) {
			throw new NotFoundException("No match for id: "+id);
		}else {
			tempUser.setAreaOfInterest(user.getAreaOfInterest());
			tempUser.setEmail(user.getEmail());
			return repository.save(tempUser);
		}
	
	}
	
	
	//Delete user
	@Transactional
	@PostMapping("/delete/{id}")
	public User deleteUserById(@PathVariable("id") Integer id) {
		User user = repository.findById(id).get();
		if(user == null) {
			throw new NotFoundException("No match for id: "+id);
		}else {
			repository.deleteById(id);
			return user;
		}
		
	}
	
	//Retrieve list of users
	@GetMapping("/showUsers")
	public List<String> getUsers(){
		List<String> names = new ArrayList<String>();
		List<User> user = repository.findAll();
		for(User i: user) {
			names.add(i.getName());
		}
		return names;
	}
	
	
	//Retrieve specific user filtered by name
	@GetMapping("/showUsers/name/{name}")
	public List<User> getUsersByName(@PathVariable("name") String name) {
		List<User> user = repository.findByName(name);
		if(user.isEmpty()) {
			throw new NotFoundException("No user named: "+ name);
		}else {
			return user;
		}
	}
	
	
	//Retrieve specific user filtered by email
	@GetMapping("/showUsers/email/{email}")
	public List<User> getUsersByEmail(@PathVariable("email") String email) {
		List<User> user = repository.findByEmail(email);
		if(user.isEmpty()) {
			throw new NotFoundException("No user with email: "+ email);
		}else {
			return user;
		}
	}
	
}