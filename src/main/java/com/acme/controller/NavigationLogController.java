package com.acme.controller;

import com.acme.model.NavigationLog;
import com.acme.repository.NavigationLogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/nav")
@CrossOrigin(origins = "*")
public class NavigationLogController {

    private final NavigationLogRepository navLogRepository;

    public NavigationLogController(NavigationLogRepository navLogRepository) {
        this.navLogRepository = navLogRepository;
    }

    /**
     * POST /api/nav
     * Records a product view or cart action.
     * Documents auto-expire after 30 days via TTL index.
     * Replaces ACME's nightly batch deletion job.
     */
    @PostMapping
    public ResponseEntity<NavigationLog> logAction(@RequestBody NavigationLog log) {
        log.setTimestamp(Instant.now());
        log.setCreatedAt(Instant.now());
        return ResponseEntity.ok(navLogRepository.save(log));
    }
}
