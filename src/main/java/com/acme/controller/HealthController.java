package com.acme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "ACME Fashion API",
            "database", "MongoDB Atlas"
        ));
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> root() {
        return ResponseEntity.ok(Map.of(
            "service", "ACME Fashion API",
            "version", "1.0.0",
            "endpoints", "/api/products, /api/orders, /api/nav, /health"
        ));
    }
}
