package com.primefuel.fulltank.platform.ordering.domain.model.valueobjects;

public enum OrderStatus {
    PENDING,
    CONFIRMED,
    DISPATCHED,
    PENDING_PAYMENT,
    PAID,
    IN_PROGRESS,
    DELIVERED,
    CANCELLED
}
