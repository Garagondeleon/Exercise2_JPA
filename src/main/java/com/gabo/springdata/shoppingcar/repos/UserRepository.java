package com.gabo.springdata.shoppingcar.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.gabo.springdata.shoppingcar.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>, CrudRepository<User,Integer> {

	List<User>findByName(String name);

	List<User>findByEmail(String email);
	
}
