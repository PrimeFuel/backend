package com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates.Driver;
import com.primefuel.fulltank.platform.fulfillment.domain.repositories.DriverRepository;
import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.assemblers.DriverPersistenceAssembler;
import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.repositories.DriverPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DriverRepositoryImpl implements DriverRepository {

    private final DriverPersistenceRepository persistenceRepository;

    public DriverRepositoryImpl(DriverPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<Driver> findById(Long id) {
        return persistenceRepository.findById(id).map(DriverPersistenceAssembler::toDomain);
    }

    @Override
    public List<Driver> findByProviderId(Long providerId) {
        return persistenceRepository.findByProviderId(providerId).stream()
                .map(DriverPersistenceAssembler::toDomain).toList();
    }

    @Override
    public Driver save(Driver driver) {
        return DriverPersistenceAssembler.toDomain(
                persistenceRepository.save(DriverPersistenceAssembler.toPersistence(driver)));
    }

    @Override
    public void deleteById(Long id) {
        persistenceRepository.deleteById(id);
    }
}
