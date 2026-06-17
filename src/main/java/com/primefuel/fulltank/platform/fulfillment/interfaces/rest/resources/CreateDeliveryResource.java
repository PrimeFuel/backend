package com.primefuel.fulltank.platform.fulfillment.interfaces.rest.resources;

public record CreateDeliveryResource(Long orderId, Long providerId, Long driverId, Long vehicleId,
                                     String scheduledDate, String notes) {
}
