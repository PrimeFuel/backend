package com.primefuel.fulltank.platform.fulfillment.domain.repositories;

import com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository {
    Optional<Delivery> findById(Long id);
    Optional<Delivery> findByOrderId(Long orderId);
    List<Delivery> findByProviderId(Long providerId);
    List<Delivery> findAll();
    Delivery save(Delivery delivery);
}
