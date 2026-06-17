package com.primefuel.fulltank.platform.equipment.interfaces.rest.transform;

import com.primefuel.fulltank.platform.equipment.domain.model.aggregates.Equipment;
import com.primefuel.fulltank.platform.equipment.interfaces.rest.resources.EquipmentResource;

public final class EquipmentResourceFromEntityAssembler {

    private EquipmentResourceFromEntityAssembler() {
    }

    public static EquipmentResource toResourceFromEntity(Equipment equipment) {
        return new EquipmentResource(equipment.getId(), equipment.getName(), equipment.getEquipmentType(),
                equipment.getLicensePlate(), equipment.getFuelType(), equipment.getTankCapacity(), equipment.getCurrentLevel(),
                equipment.getLocation(), equipment.getStatus(), equipment.getAutoRefill(), equipment.getRefillThreshold(),
                equipment.getLastRefillDate(), equipment.getCompanyId(), equipment.getFavoriteProviderId());
    }
}
