package com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates.Vehicle;
import com.primefuel.fulltank.platform.fulfillment.domain.repositories.VehicleRepository;
import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.assemblers.VehiclePersistenceAssembler;
import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.repositories.VehiclePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    private final VehiclePersistenceRepository persistenceRepository;

    public VehicleRepositoryImpl(VehiclePersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return persistenceRepository.findById(id).map(VehiclePersistenceAssembler::toDomain);
    }

    @Override
    public List<Vehicle> findByProviderId(Long providerId) {
        return persistenceRepository.findByProviderId(providerId).stream()
                .map(VehiclePersistenceAssembler::toDomain).toList();
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return VehiclePersistenceAssembler.toDomain(
                persistenceRepository.save(VehiclePersistenceAssembler.toPersistence(vehicle)));
    }

    @Override
    public void deleteById(Long id) {
        persistenceRepository.deleteById(id);
    }
}
