package com.gabo.springdata.shoppingcar.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.gabo.springdata.shoppingcar.entities.OrderHistory;

public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Integer>, JpaRepository<OrderHistory, Integer> {

}
