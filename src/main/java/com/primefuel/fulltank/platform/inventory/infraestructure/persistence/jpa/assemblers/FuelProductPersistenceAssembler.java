package com.primefuel.fulltank.platform.inventory.infrastructure.persistence.jpa.assemblers;

import com.primefuel.fulltank.platform.inventory.domain.model.aggregates.FuelProduct;
import com.primefuel.fulltank.platform.inventory.infrastructure.persistence.jpa.entities.FuelProductPersistenceEntity;

public final class FuelProductPersistenceAssembler {

    private FuelProductPersistenceAssembler() {
    }

    public static FuelProduct toDomainFromPersistence(FuelProductPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new FuelProduct();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setFuelType(entity.getFuelType());
        domain.setPricePerUnit(entity.getPricePerUnit());
        domain.setUnit(entity.getUnit());
        domain.setAvailableStock(entity.getAvailableStock());
        domain.setCapacity(entity.getCapacity());
        domain.setProviderId(entity.getProviderId());
        domain.setActive(entity.getActive() == null || entity.getActive());
        return domain;
    }

    public static FuelProductPersistenceEntity toPersistenceFromDomain(FuelProduct domain) {
        if (domain == null) return null;
        var entity = new FuelProductPersistenceEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setName(domain.getName());
        entity.setFuelType(domain.getFuelType());
        entity.setPricePerUnit(domain.getPricePerUnit());
        entity.setUnit(domain.getUnit());
        entity.setAvailableStock(domain.getAvailableStock());
        entity.setCapacity(domain.getCapacity());
        entity.setProviderId(domain.getProviderId());
        entity.setActive(domain.getActive() == null || domain.getActive());
        return entity;
    }
}
