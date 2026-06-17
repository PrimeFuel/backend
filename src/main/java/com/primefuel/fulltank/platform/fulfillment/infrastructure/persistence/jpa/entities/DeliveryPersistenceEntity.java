package com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.entities;

import com.primefuel.fulltank.platform.fulfillment.domain.model.valueobjects.DeliveryStatus;
import com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private Long driverId;

    @Column(nullable = false)
    private Long vehicleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    private LocalDateTime dispatchedAt;

    private LocalDateTime deliveredAt;

    private String scheduledDate;

    @Column(columnDefinition = "TEXT")
    private String notes;
}
