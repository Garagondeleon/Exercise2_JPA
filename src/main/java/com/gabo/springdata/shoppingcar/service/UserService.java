package com.gabo.springdata.shoppingcar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabo.springdata.shoppingcar.entities.User;
import com.gabo.springdata.shoppingcar.exceptions.NotFoundException;
import com.gabo.springdata.shoppingcar.repos.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	// Insert user into table
	public User insertUser(User user) {
		List<User> tempUser = repository.findByEmail(user.getName());
		if (tempUser.isEmpty()) {
			return repository.save(user);
		}
		return user;
	}

	// Update an existing user
	public User updateUserById(int id, User user) {
		User tempUser = repository.findById(id).get();
		if (tempUser == null) {
			throw new NotFoundException("No match for id: " + id);
		} else {
			tempUser.setAreaOfInterest(user.getAreaOfInterest());
			tempUser.setEmail(user.getEmail());
			return repository.save(tempUser);
		}

	}

	// Delete user
	public User deleteUserById(int id) {
		User user = repository.findById(id).get();
		if (user == null) {
			throw new NotFoundException("No match for id: " + id);
		} else {
			repository.deleteById(id);
			return user;
		}

	}

	// Retrieve list of users
	public List<String> getUsers() {
		List<String> names = new ArrayList<String>();
		List<User> user = repository.findAll();
		for (User i : user) {
			names.add(i.getName());
		}
		return names;
	}

	// Retrieve specific user filtered by name
	public List<User> getUsersByName(String name) {
		List<User> user = repository.findByName(name);
		if (user.isEmpty()) {
			throw new NotFoundException("No user named: " + name);
		} else {
			return user;
		}
	}

	// Retrieve specific user filtered by email
	public List<User> getUsersByEmail(String email) {
		List<User> user = repository.findByEmail(email);
		if (user.isEmpty()) {
			throw new NotFoundException("No user with email: " + email);
		} else {
			return user;
		}
	}

}
