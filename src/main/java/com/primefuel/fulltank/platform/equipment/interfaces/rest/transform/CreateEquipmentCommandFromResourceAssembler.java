package com.primefuel.fulltank.platform.equipment.interfaces.rest.transform;

import com.primefuel.fulltank.platform.equipment.domain.model.commands.CreateEquipmentCommand;
import com.primefuel.fulltank.platform.equipment.interfaces.rest.resources.CreateEquipmentResource;

public final class CreateEquipmentCommandFromResourceAssembler {

    private CreateEquipmentCommandFromResourceAssembler() {
    }

    public static CreateEquipmentCommand toCommandFromResource(CreateEquipmentResource resource) {
        return new CreateEquipmentCommand(resource.name(), resource.equipmentType(), resource.licensePlate(),
                resource.fuelType(), resource.tankCapacity(), resource.currentLevel(),resource.location(),
                resource.status(), resource.autoRefill(), resource.refillThreshold(), resource.lastRefillDate(),
                resource.companyId(), resource.favoriteProviderId());
    }
}
