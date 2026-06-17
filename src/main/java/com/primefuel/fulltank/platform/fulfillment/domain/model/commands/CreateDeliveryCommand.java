package com.primefuel.fulltank.platform.fulfillment.domain.model.commands;

public record CreateDeliveryCommand(Long orderId, Long providerId, Long driverId, Long vehicleId,
                                    String scheduledDate, String notes) {
}
