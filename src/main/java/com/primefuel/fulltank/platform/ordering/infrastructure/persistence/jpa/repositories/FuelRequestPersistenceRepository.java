package com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.entities.FuelRequestPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelRequestPersistenceRepository extends JpaRepository<FuelRequestPersistenceEntity, Long> {
    List<FuelRequestPersistenceEntity> findByBuyerCompanyId(Long buyerCompanyId);
    List<FuelRequestPersistenceEntity> findByProviderId(Long providerId);
}
