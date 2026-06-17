package com.primefuel.fulltank.platform.notification.domain.model.valueobjects;

public enum NotificationType {
    NEW_REQUEST,
    REQUEST_PENDING,
    ORDER_ACCEPTED,
    ORDER_REJECTED,
    ORDER_DISPATCHED,
    ORDER_DELIVERED,
    ORDER_CONFIRMED,
    ORDER_CANCELLED,
    DELIVERY_DISPATCHED,
    DELIVERY_COMPLETED,
    DELIVERY_FAILED,
    PAYMENT_RECEIVED,
    PAYMENT_COMPLETED,
    PAYMENT_REFUNDED,
    GENERAL
}