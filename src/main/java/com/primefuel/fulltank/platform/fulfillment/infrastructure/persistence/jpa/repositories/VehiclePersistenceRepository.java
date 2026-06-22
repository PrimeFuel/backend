package com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.entities.VehiclePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiclePersistenceRepository extends JpaRepository<VehiclePersistenceEntity, Long> {
    List<VehiclePersistenceEntity> findByProviderId(Long providerId);
}
