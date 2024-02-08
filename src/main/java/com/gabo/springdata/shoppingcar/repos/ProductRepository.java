package com.gabo.springdata.shoppingcar.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.gabo.springdata.shoppingcar.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, CrudRepository<Product,Integer> {

	List<Product> findByName(String name);

	List<Product> findByPriceBetween(int minPrice, int maxPrice);

}
