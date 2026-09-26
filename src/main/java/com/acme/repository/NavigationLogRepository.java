package com.acme.repository;

import com.acme.model.NavigationLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NavigationLogRepository extends MongoRepository<NavigationLog, String> {
}
