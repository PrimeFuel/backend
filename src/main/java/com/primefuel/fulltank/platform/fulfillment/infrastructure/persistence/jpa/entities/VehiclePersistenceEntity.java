package com.primefuel.fulltank.platform.fulfillment.infrastructure.persistence.jpa.entities;

import com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
public class VehiclePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false, unique = true, length = 20)
    private String licensePlate;

    @Column(nullable = false, length = 80)
    private String brand;

    @Column(nullable = false, length = 80)
    private String model;

    @Column(nullable = false)
    private Double capacity;

    @Column(nullable = false, length = 20)
    private String unit;

    @Column(nullable = false, length = 30)
    private String status;
}
