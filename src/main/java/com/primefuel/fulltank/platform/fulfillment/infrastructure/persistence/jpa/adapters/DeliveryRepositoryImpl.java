package com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates.Delivery;
import com.primefuel.fulltank.platform.fulfillment.domain.repositories.DeliveryRepository;
import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.assemblers.DeliveryPersistenceAssembler;
import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.repositories.DeliveryPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {

    private final DeliveryPersistenceRepository deliveryPersistenceRepository;

    public DeliveryRepositoryImpl(DeliveryPersistenceRepository deliveryPersistenceRepository) {
        this.deliveryPersistenceRepository = deliveryPersistenceRepository;
    }

    @Override
    public Optional<Delivery> findById(Long id) {
        return deliveryPersistenceRepository.findById(id)
                .map(DeliveryPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<Delivery> findByOrderId(Long orderId) {
        return deliveryPersistenceRepository.findByOrderId(orderId)
                .map(DeliveryPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryPersistenceRepository.findAll().stream()
                .map(DeliveryPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<Delivery> findByProviderId(Long providerId) {
        return deliveryPersistenceRepository.findByProviderId(providerId).stream()
                .map(DeliveryPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Delivery save(Delivery delivery) {
        var entity = DeliveryPersistenceAssembler.toPersistenceFromDomain(delivery);
        return DeliveryPersistenceAssembler.toDomainFromPersistence(
                deliveryPersistenceRepository.save(entity));
    }
}
