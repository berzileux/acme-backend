package com.acme.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * Navigation log — records browsing behaviour.
 *
 * TTL index automatically deletes documents after 30 days.
 * This replaces ACME's nightly batch deletion job entirely.
 */
@Data
@Document(collection = "navigation_logs")
public class NavigationLog {

    @Id
    private String id;

    private String sessionId;
    private String productId;
    private String action;
    private Instant timestamp;

    @Indexed(expireAfter = "30d")
    private Instant createdAt;
}
