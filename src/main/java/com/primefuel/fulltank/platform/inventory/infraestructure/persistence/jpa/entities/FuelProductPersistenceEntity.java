package com.primefuel.fulltank.platform.inventory.infrastructure.persistence.jpa.entities;

import com.primefuel.fulltank.platform.inventory.domain.model.valueobjects.FuelType;
import com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fuel_products")
@Getter
@Setter
@NoArgsConstructor
public class FuelProductPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private FuelType fuelType;

    @Column(nullable = false)
    private Double pricePerUnit;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Double availableStock;

    @Column
    private Double capacity;

    @Column(nullable = false)
    private Long providerId;

    @Column
    private Boolean active = true;
}
