package com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.entities.DeliveryPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryPersistenceRepository extends JpaRepository<DeliveryPersistenceEntity, Long> {
    Optional<DeliveryPersistenceEntity> findByOrderId(Long orderId);
    java.util.List<DeliveryPersistenceEntity> findByProviderId(Long providerId);
}
