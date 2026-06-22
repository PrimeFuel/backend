package com.primefuel.fulltank.platform.equipment.infrastructure.persistence.jpa.assemblers;

import com.primefuel.fulltank.platform.equipment.domain.model.aggregates.Equipment;
import com.primefuel.fulltank.platform.equipment.infrastructure.persistence.jpa.entities.EquipmentPersistenceEntity;

public final class EquipmentPersistenceAssembler {

    private EquipmentPersistenceAssembler() {
    }

    public static Equipment toDomainFromPersistence(EquipmentPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new Equipment();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setEquipmentType(entity.getEquipmentType());
        domain.setLicensePlate(entity.getLicensePlate());
        domain.setFuelType(entity.getFuelType());
        domain.setTankCapacity(entity.getTankCapacity());
        domain.setCurrentLevel(entity.getCurrentLevel() != null ? entity.getCurrentLevel() : 0.0);
        domain.setLocation(entity.getLocation());
        domain.setStatus(entity.getStatus() != null ? entity.getStatus() : "operational");
        domain.setAutoRefill(entity.getAutoRefill() != null ? entity.getAutoRefill() : false);
        domain.setRefillThreshold(entity.getRefillThreshold() != null ? entity.getRefillThreshold() : 20);
        domain.setLastRefillDate(entity.getLastRefillDate());
        domain.setCompanyId(entity.getCompanyId());
        domain.setFavoriteProviderId(entity.getFavoriteProviderId());
        return domain;
    }

    public static EquipmentPersistenceEntity toPersistenceFromDomain(Equipment domain) {
        if (domain == null) return null;
        var entity = new EquipmentPersistenceEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setName(domain.getName());
        entity.setEquipmentType(domain.getEquipmentType());
        entity.setLicensePlate(domain.getLicensePlate());
        entity.setFuelType(domain.getFuelType());
        entity.setTankCapacity(domain.getTankCapacity());
        entity.setCurrentLevel(domain.getCurrentLevel() != null ? domain.getCurrentLevel() : 0.0);
        entity.setLocation(domain.getLocation());
        entity.setStatus(domain.getStatus());
        entity.setAutoRefill(domain.getAutoRefill());
        entity.setRefillThreshold(domain.getRefillThreshold());
        entity.setLastRefillDate(domain.getLastRefillDate());
        entity.setCompanyId(domain.getCompanyId());
        entity.setFavoriteProviderId(domain.getFavoriteProviderId());
        return entity;
    }
}
