package com.primefuel.fulltank.platform.inventory.domain.repositories;

import com.primefuel.fulltank.platform.inventory.domain.model.aggregates.FuelProduct;

import java.util.List;
import java.util.Optional;

public interface FuelProductRepository {
    Optional<FuelProduct> findById(Long id);
    List<FuelProduct> findAll();
    List<FuelProduct> findByProviderId(Long providerId);
    FuelProduct save(FuelProduct fuelProduct);
    boolean existsById(Long id);
    void deleteById(Long id);
}
