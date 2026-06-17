package com.primefuel.fulltank.platform.equipment.domain.model.aggregates;

import com.primefuel.fulltank.platform.equipment.domain.model.commands.CreateEquipmentCommand;
import com.primefuel.fulltank.platform.equipment.domain.model.commands.UpdateEquipmentCommand;
import com.primefuel.fulltank.platform.equipment.domain.model.valueobjects.EquipmentType;
import com.primefuel.fulltank.platform.inventory.domain.model.valueobjects.FuelType;
import com.primefuel.fulltank.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Equipment extends AbstractDomainAggregateRoot<Equipment> {

    private Long id;
    private String name;
    private EquipmentType equipmentType;
    private String licensePlate;
    private FuelType fuelType;
    private Double tankCapacity;
    private Double currentLevel;
    private String location;
    private String status;
    private Boolean autoRefill;
    private Integer refillThreshold;
    private String lastRefillDate;
    private Long companyId;
    private Long favoriteProviderId;

    public Equipment(CreateEquipmentCommand command) {
        this.name = command.name();
        this.equipmentType = command.equipmentType();
        this.licensePlate = command.licensePlate();
        this.fuelType = command.fuelType();
        this.tankCapacity = command.tankCapacity();
        this.currentLevel = command.currentLevel();
        this.location = command.location();
        this.status = command.status();
        this.autoRefill = command.autoRefill();
        this.refillThreshold = command.refillThreshold();
        this.lastRefillDate = command.lastRefillDate();
        this.companyId = command.companyId();
        this.favoriteProviderId = command.favoriteProviderId();
    }

    public void update(UpdateEquipmentCommand command) {
        this.name = command.name();
        this.equipmentType = command.equipmentType();
        this.licensePlate = command.licensePlate();
        this.fuelType = command.fuelType();
        this.tankCapacity = command.tankCapacity();
        this.currentLevel = command.currentLevel();
        this.location = command.location();
        this.status = command.status();
        this.autoRefill = command.autoRefill();
        this.refillThreshold = command.refillThreshold();
        this.lastRefillDate = command.lastRefillDate();
        this.favoriteProviderId = command.favoriteProviderId();
    }

    public void assignFavoriteProvider(Long providerId) {
        this.favoriteProviderId = providerId;
    }

    public void receiveFuel(Double quantity) {
        this.currentLevel = Math.min(this.tankCapacity, this.currentLevel + quantity);
    }
}
