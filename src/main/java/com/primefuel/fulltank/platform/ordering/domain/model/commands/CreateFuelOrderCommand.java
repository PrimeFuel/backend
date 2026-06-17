package com.primefuel.fulltank.platform.ordering.domain.model.commands;

import java.time.LocalDate;

public record CreateFuelOrderCommand(
        Long companyId,
        Long providerId,
        Long fuelProductId,
        Long equipmentId,
        Double requestedQuantity,
        String deliveryAddress,
        LocalDate scheduledDate) {
}
