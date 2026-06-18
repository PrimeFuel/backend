package com.primefuel.fulltank.platform.inventory.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.inventory.domain.model.aggregates.FuelProduct;
import com.primefuel.fulltank.platform.inventory.domain.repositories.FuelProductRepository;
import com.primefuel.fulltank.platform.inventory.infrastructure.persistence.jpa.assemblers.FuelProductPersistenceAssembler;
import com.primefuel.fulltank.platform.inventory.infrastructure.persistence.jpa.repositories.FuelProductPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FuelProductRepositoryImpl implements FuelProductRepository {

    private final FuelProductPersistenceRepository fuelProductPersistenceRepository;

    public FuelProductRepositoryImpl(FuelProductPersistenceRepository fuelProductPersistenceRepository) {
        this.fuelProductPersistenceRepository = fuelProductPersistenceRepository;
    }

    @Override
    public Optional<FuelProduct> findById(Long id) {
        return fuelProductPersistenceRepository.findById(id)
                .map(FuelProductPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<FuelProduct> findAll() {
        return fuelProductPersistenceRepository.findAll().stream()
                .map(FuelProductPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<FuelProduct> findByProviderId(Long providerId) {
        return fuelProductPersistenceRepository.findByProviderId(providerId).stream()
                .map(FuelProductPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public FuelProduct save(FuelProduct fuelProduct) {
        var entity = FuelProductPersistenceAssembler.toPersistenceFromDomain(fuelProduct);
        return FuelProductPersistenceAssembler.toDomainFromPersistence(
                fuelProductPersistenceRepository.save(entity));
    }

    @Override
    public boolean existsById(Long id) {
        return fuelProductPersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        fuelProductPersistenceRepository.deleteById(id);
    }
}

