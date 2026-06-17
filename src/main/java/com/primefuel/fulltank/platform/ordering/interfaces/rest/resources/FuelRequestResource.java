package com.primefuel.fulltank.platform.ordering.interfaces.rest.resources;

import com.primefuel.fulltank.platform.ordering.domain.model.valueobjects.RequestStatus;

import java.time.LocalDate;
import java.util.Date;

public record FuelRequestResource(
        Long id, Long buyerCompanyId, Long providerId, Long equipmentId, Long fuelProductId,
        String fuelType, String productName, Double quantity, String unit, Double unitPrice,
        String deliveryAddress, LocalDate deliveryDate, RequestStatus status, String source,
        String rejectionReason, Date createdAt, Date updatedAt) {
}
