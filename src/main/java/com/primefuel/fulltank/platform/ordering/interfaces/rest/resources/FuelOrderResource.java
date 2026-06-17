package com.primefuel.fulltank.platform.ordering.interfaces.rest.resources;

import com.primefuel.fulltank.platform.ordering.domain.model.valueobjects.OrderStatus;

import java.time.LocalDate;

public record FuelOrderResource(Long id, Long requestId, Long companyId, Long providerId, Long fuelProductId,
                                Long equipmentId, Double requestedQuantity, Double totalPrice,
                                OrderStatus status, String deliveryAddress, LocalDate scheduledDate) {
}
