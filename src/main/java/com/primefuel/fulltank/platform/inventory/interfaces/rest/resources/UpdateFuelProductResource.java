package com.primefuel.fulltank.platform.inventory.interfaces.rest.resources;

import com.primefuel.fulltank.platform.inventory.domain.model.valueobjects.FuelType;

public record UpdateFuelProductResource(String name, FuelType fuelType, Double pricePerUnit, String unit, Double availableStock, Double capacity, Boolean active) {
}
