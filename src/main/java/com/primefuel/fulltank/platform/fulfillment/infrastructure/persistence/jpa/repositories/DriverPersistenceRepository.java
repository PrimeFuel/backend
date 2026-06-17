package com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.entities.DriverPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverPersistenceRepository extends JpaRepository<DriverPersistenceEntity, Long> {
    List<DriverPersistenceEntity> findByProviderId(Long providerId);
}
