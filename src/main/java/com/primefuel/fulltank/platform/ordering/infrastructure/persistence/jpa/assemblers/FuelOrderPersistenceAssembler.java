package com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.assemblers;

import com.primefuel.fulltank.platform.ordering.domain.model.aggregates.FuelOrder;
import com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.entities.FuelOrderPersistenceEntity;

public final class FuelOrderPersistenceAssembler {

    private FuelOrderPersistenceAssembler() {
    }

    public static FuelOrder toDomainFromPersistence(FuelOrderPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new FuelOrder();
        domain.setId(entity.getId());
        domain.setRequestId(entity.getRequestId());
        domain.setCompanyId(entity.getCompanyId());
        domain.setProviderId(entity.getProviderId());
        domain.setFuelProductId(entity.getFuelProductId());
        domain.setEquipmentId(entity.getEquipmentId());
        domain.setRequestedQuantity(entity.getRequestedQuantity());
        domain.setTotalPrice(entity.getTotalPrice());
        domain.setStatus(entity.getStatus());
        domain.setDeliveryAddress(entity.getDeliveryAddress());
        domain.setScheduledDate(entity.getScheduledDate());
        return domain;
    }

    public static FuelOrderPersistenceEntity toPersistenceFromDomain(FuelOrder domain) {
        if (domain == null) return null;
        var entity = new FuelOrderPersistenceEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setRequestId(domain.getRequestId());
        entity.setCompanyId(domain.getCompanyId());
        entity.setProviderId(domain.getProviderId());
        entity.setFuelProductId(domain.getFuelProductId());
        entity.setEquipmentId(domain.getEquipmentId());
        entity.setRequestedQuantity(domain.getRequestedQuantity());
        entity.setTotalPrice(domain.getTotalPrice());
        entity.setStatus(domain.getStatus());
        entity.setDeliveryAddress(domain.getDeliveryAddress());
        entity.setScheduledDate(domain.getScheduledDate());
        return entity;
    }
}
