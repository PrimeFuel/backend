package com.primefuel.fulltank.platform.ordering.domain.repositories;

import com.primefuel.fulltank.platform.ordering.domain.model.aggregates.FuelOrder;

import java.util.List;
import java.util.Optional;

public interface FuelOrderRepository {
    Optional<FuelOrder> findById(Long id);
    List<FuelOrder> findAll();
    List<FuelOrder> findByCompanyId(Long companyId);
    List<FuelOrder> findByProviderId(Long providerId);
    FuelOrder save(FuelOrder fuelOrder);
}
