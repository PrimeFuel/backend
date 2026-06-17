package com.primefuel.fulltank.platform.equipment.interfaces.rest.transform;

import com.primefuel.fulltank.platform.equipment.domain.model.commands.UpdateEquipmentCommand;
import com.primefuel.fulltank.platform.equipment.interfaces.rest.resources.UpdateEquipmentResource;

public final class UpdateEquipmentCommandFromResourceAssembler {

    private UpdateEquipmentCommandFromResourceAssembler() {
    }

    public static UpdateEquipmentCommand toCommandFromResource(Long equipmentId, UpdateEquipmentResource resource) {
        return new UpdateEquipmentCommand(equipmentId, resource.name(), resource.equipmentType(),
                resource.licensePlate(), resource.fuelType(), resource.tankCapacity(), resource.currentLevel(),
                resource.location(), resource.status(), resource.autoRefill(), resource.refillThreshold(),
                resource.lastRefillDate(), resource.favoriteProviderId());
    }
}
