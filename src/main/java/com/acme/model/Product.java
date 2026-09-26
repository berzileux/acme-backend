package com.acme.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.Instant;
import java.util.List;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;
    private String category;
    private Double price;
    private String sku;
    private String description;
    private String color;
    private List<String> sizes;
    private Integer stock;
    private String image;
}
