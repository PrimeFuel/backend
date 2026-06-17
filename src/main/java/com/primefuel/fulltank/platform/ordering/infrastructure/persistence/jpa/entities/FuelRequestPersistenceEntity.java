package com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.entities;

import com.primefuel.fulltank.platform.ordering.domain.model.valueobjects.RequestStatus;
import com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "fuel_requests")
@Getter
@Setter
@NoArgsConstructor
public class FuelRequestPersistenceEntity extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false) private Long buyerCompanyId;
    @Column(nullable = false) private Long providerId;
    private Long equipmentId;
    @Column(nullable = false) private Long fuelProductId;
    @Column(nullable = false, length = 30) private String fuelType;
    @Column(nullable = false) private String productName;
    @Column(nullable = false) private Double quantity;
    @Column(nullable = false, length = 20) private String unit;
    @Column(nullable = false) private Double unitPrice;
    @Column(nullable = false) private String deliveryAddress;
    @Column(nullable = false) private LocalDate deliveryDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20) private RequestStatus status;
    @Column(nullable = false, length = 20) private String source;
    @Column(length = 240) private String rejectionReason;
}
