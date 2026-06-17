package com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.assemblers;

import com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates.Vehicle;
import com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.entities.VehiclePersistenceEntity;

public final class VehiclePersistenceAssembler {

    private VehiclePersistenceAssembler() {
    }

    public static Vehicle toDomain(VehiclePersistenceEntity entity) {
        var vehicle = new Vehicle();
        vehicle.setId(entity.getId());
        vehicle.setProviderId(entity.getProviderId());
        vehicle.setLicensePlate(entity.getLicensePlate());
        vehicle.setBrand(entity.getBrand());
        vehicle.setModel(entity.getModel());
        vehicle.setCapacity(entity.getCapacity());
        vehicle.setUnit(entity.getUnit());
        vehicle.setStatus(entity.getStatus());
        return vehicle;
    }

    public static VehiclePersistenceEntity toPersistence(Vehicle vehicle) {
        var entity = new VehiclePersistenceEntity();
        if (vehicle.getId() != null) entity.setId(vehicle.getId());
        entity.setProviderId(vehicle.getProviderId());
        entity.setLicensePlate(vehicle.getLicensePlate());
        entity.setBrand(vehicle.getBrand());
        entity.setModel(vehicle.getModel());
        entity.setCapacity(vehicle.getCapacity());
        entity.setUnit(vehicle.getUnit());
        entity.setStatus(vehicle.getStatus());
        return entity;
    }
}
