package com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.assemblers;

import com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates.Delivery;
import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.entities.DeliveryPersistenceEntity;

public final class DeliveryPersistenceAssembler {

    private DeliveryPersistenceAssembler() {
    }

    public static Delivery toDomainFromPersistence(DeliveryPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new Delivery();
        domain.setId(entity.getId());
        domain.setOrderId(entity.getOrderId());
        domain.setProviderId(entity.getProviderId());
        domain.setDriverId(entity.getDriverId());
        domain.setVehicleId(entity.getVehicleId());
        domain.setStatus(entity.getStatus());
        domain.setScheduledDate(entity.getScheduledDate());
        domain.setDispatchedAt(entity.getDispatchedAt());
        domain.setDeliveredAt(entity.getDeliveredAt());
        domain.setNotes(entity.getNotes());
        return domain;
    }

    public static DeliveryPersistenceEntity toPersistenceFromDomain(Delivery domain) {
        if (domain == null) return null;
        var entity = new DeliveryPersistenceEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setOrderId(domain.getOrderId());
        entity.setProviderId(domain.getProviderId());
        entity.setDriverId(domain.getDriverId());
        entity.setVehicleId(domain.getVehicleId());
        entity.setStatus(domain.getStatus());
        entity.setScheduledDate(domain.getScheduledDate());
        entity.setDispatchedAt(domain.getDispatchedAt());
        entity.setDeliveredAt(domain.getDeliveredAt());
        entity.setNotes(domain.getNotes());
        return entity;
    }
}
