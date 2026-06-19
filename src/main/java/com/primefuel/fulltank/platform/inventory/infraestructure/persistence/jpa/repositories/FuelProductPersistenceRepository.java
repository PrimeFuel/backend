package com.primefuel.fulltank.platform.inventory.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.inventory.infrastructure.persistence.jpa.entities.FuelProductPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelProductPersistenceRepository extends JpaRepository<FuelProductPersistenceEntity, Long> {
    List<FuelProductPersistenceEntity> findByProviderId(Long providerId);
}
