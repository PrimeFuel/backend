package com.primefuel.fulltank.platform.fulfillment.interfaces.rest.resources;

import com.primefuel.fulltank.platform.fulfillment.domain.model.valueobjects.DeliveryStatus;

import java.time.LocalDateTime;

public record DeliveryResource(Long id, Long orderId, Long providerId, Long driverId, Long vehicleId,
                               DeliveryStatus status, String scheduledDate, LocalDateTime dispatchedAt,
                               LocalDateTime deliveredAt, String notes) {
}
