package com.primefuel.fulltank.platform.fulfillment.domain.model.commands;

public record FailDeliveryCommand(Long deliveryId, String reason) {
}
