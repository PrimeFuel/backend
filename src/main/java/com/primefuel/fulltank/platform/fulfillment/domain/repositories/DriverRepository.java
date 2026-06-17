package com.primefuel.fulltank.platform.fulfillment.domain.repositories;

import com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverRepository {
    Optional<Driver> findById(Long id);
    List<Driver> findByProviderId(Long providerId);
    Driver save(Driver driver);
    void deleteById(Long id);
}
