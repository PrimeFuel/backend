package com.primefuel.fulltank.platform.inventory.domain.model.commands;

public record UpdateFuelProductStockCommand(Long fuelProductId, Double newStock) {
}

