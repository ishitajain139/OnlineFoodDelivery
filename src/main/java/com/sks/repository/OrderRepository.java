package com.sks.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.sks.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByRestaurantId(Long restaurantId);

    List<Order> findByOrderDate(LocalDateTime orderDate);

    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
