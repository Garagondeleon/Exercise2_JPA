package com.gabo.springdata.shoppingcar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gabo.springdata.shoppingcar.entities.User;
import com.gabo.springdata.shoppingcar.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Insert user into table
	@PostMapping("/insert")
	public User insertUser(@Valid @RequestBody User user) {
		return userService.insertUser(user);
	}
	
	//Update an existing user
	@PostMapping("/update/{id}")
	public User updateUserById( @PathVariable("id") int id,@Validated @RequestBody User user) {
		return userService.updateUserById(id, user);
	}
	
	//Delete user
	@Transactional
	@PostMapping("/delete/{id}")
	public User deleteUserById(@PathVariable("id") int id) {
		return userService.deleteUserById(id);
	}
	
	//Retrieve list of users
	@GetMapping("/showUsers")
	public List<String> getUsers(){
		return userService.getUsers();
	}
	
	//Retrieve specific user filtered by name
	@GetMapping("/showUsers/name/{name}")
	public List<User> getUsersByName(@PathVariable("name") String name) {
		return userService.getUsersByName(name);
	}
	
	//Retrieve specific user filtered by email
	@GetMapping("/showUsers/email/{email}")
	public List<User> getUsersByEmail(@PathVariable("email") String email) {
		return userService.getUsersByEmail(email);
	}
	
}