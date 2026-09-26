package com.acme.controller;

import com.acme.model.Order;
import com.acme.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * POST /api/orders
     * Receives the full order as one JSON document.
     * One insertOne() to MongoDB — customer, address, and all items together.
     * In MySQL this would require INSERTs into 5 separate tables.
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setStatus("confirmed");
        order.setCreatedAt(Instant.now());
        order.setCurrency("GBP");
        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return orderRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{email}")
    public List<Order> getOrdersByCustomer(@PathVariable String email) {
        return orderRepository.findByCustomerEmail(email);
    }
}
