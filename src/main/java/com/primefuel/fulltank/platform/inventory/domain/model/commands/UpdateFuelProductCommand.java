package com.primefuel.fulltank.platform.inventory.domain.model.commands;

import com.primefuel.fulltank.platform.inventory.domain.model.valueobjects.FuelType;

public record UpdateFuelProductCommand(Long fuelProductId, String name, FuelType fuelType, Double pricePerUnit, String unit, Double availableStock, Double capacity, Boolean active) {
}

