package com.primefuel.fulltank.platform.equipment.domain.model.commands;

import com.primefuel.fulltank.platform.equipment.domain.model.valueobjects.EquipmentType;
import com.primefuel.fulltank.platform.inventory.domain.model.valueobjects.FuelType;

public record UpdateEquipmentCommand(
        Long equipmentId,
        String name,
        EquipmentType equipmentType,
        String licensePlate,
        FuelType fuelType,
        Double tankCapacity,
        Double currentLevel,
        String location,
        String status,
        Boolean autoRefill,
        Integer refillThreshold,
        String lastRefillDate,
        Long favoriteProviderId) {
}
