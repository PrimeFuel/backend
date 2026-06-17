package com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.entities.FuelOrderPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelOrderPersistenceRepository extends JpaRepository<FuelOrderPersistenceEntity, Long> {
    List<FuelOrderPersistenceEntity> findByCompanyId(Long companyId);
    List<FuelOrderPersistenceEntity> findByProviderId(Long providerId);
}
