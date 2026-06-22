package com.primefuel.fulltank.platform.equipment.infrastructure.persistence.jpa.entities;

import com.primefuel.fulltank.platform.equipment.domain.model.valueobjects.EquipmentType;
import com.primefuel.fulltank.platform.inventory.domain.model.valueobjects.FuelType;
import com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipment")
@Getter
@Setter
@NoArgsConstructor
public class EquipmentPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EquipmentType equipmentType;

    @Column(nullable = false)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private FuelType fuelType;

    @Column(nullable = false)
    private Double tankCapacity;

    @Column(nullable = false)
    private Double currentLevel;

    @Column
    private String location;

    @Column
    private String status;

    @Column
    private Boolean autoRefill;

    @Column
    private Integer refillThreshold;

    @Column
    private String lastRefillDate;

    @Column(nullable = false)
    private Long companyId;

    @Column
    private Long favoriteProviderId;
}
