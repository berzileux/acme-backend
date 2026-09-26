package com.acme.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

/**
 * One MongoDB document replacing what would be
 * 5 MySQL tables: customers, orders, order_items, products, addresses.
 *
 * Single insertOne() call. No JOINs ever needed to read it back.
 */
@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String status;
    private Instant createdAt;
    private Double total;
    private String currency;

    private Customer customer;
    private List<OrderItem> items;

    @Data
    public static class Customer {
        private String name;
        private String email;
        private Address address;
    }

    @Data
    public static class Address {
        private String street;
        private String city;
        private String postcode;
        private String country;
    }

    @Data
    public static class OrderItem {
        private String sku;
        private String name;
        private String size;
        private Integer qty;
        private Double price;
    }
}
