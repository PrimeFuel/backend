package com.primefuel.fulltank.platform.ordering.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateFuelRequestResource(
        Long buyerCompanyId, Long providerId, Long equipmentId, Long fuelProductId,
        String fuelType, String productName, Double quantity, String unit, Double unitPrice,
        String deliveryAddress, LocalDate deliveryDate, String source) {
}
