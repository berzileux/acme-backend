package com.acme.repository;

import com.acme.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByCustomerEmail(String email);
    List<Order> findByStatus(String status);
}
