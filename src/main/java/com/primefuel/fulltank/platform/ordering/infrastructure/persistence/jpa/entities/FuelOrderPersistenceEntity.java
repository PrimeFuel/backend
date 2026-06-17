package com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.entities;

import com.primefuel.fulltank.platform.ordering.domain.model.valueobjects.OrderStatus;
import com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Entity
@Table(name = "fuel_orders")
@Getter
@Setter
@NoArgsConstructor
public class FuelOrderPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(unique = true)
    private Long requestId;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private Long fuelProductId;

    @Column(nullable = true)
    private Long equipmentId;

    @Column(nullable = false)
    private Double requestedQuantity;

    @Column(nullable = false)
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, length = 30, columnDefinition = "VARCHAR(30)")
    private OrderStatus status;

    @Column(nullable = false)
    private String deliveryAddress;

    private LocalDate scheduledDate;
}
