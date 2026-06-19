package com.primefuel.fulltank.platform.inventory.domain.model.aggregates;

import com.primefuel.fulltank.platform.inventory.domain.model.commands.CreateFuelProductCommand;
import com.primefuel.fulltank.platform.inventory.domain.model.commands.UpdateFuelProductCommand;
import com.primefuel.fulltank.platform.inventory.domain.model.valueobjects.FuelType;
import com.primefuel.fulltank.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuelProduct extends AbstractDomainAggregateRoot<FuelProduct> {

    private Long id;
    private String name;
    private FuelType fuelType;
    private Double pricePerUnit;
    private String unit;
    private Double availableStock;
    private Double capacity;
    private Long providerId;
    /** Whether the product is published to the buyer catalog. Inactive products are hidden/blocked. */
    private Boolean active = true;

    public FuelProduct(CreateFuelProductCommand command) {
        this.name = command.name();
        this.fuelType = command.fuelType();
        this.pricePerUnit = command.pricePerUnit();
        this.unit = command.unit();
        this.availableStock = command.availableStock();
        this.capacity = command.capacity();
        this.providerId = command.providerId();
        this.active = command.active() == null || command.active();
    }

    public void updateStock(Double newStock) {
        this.availableStock = newStock;
    }

    public void update(UpdateFuelProductCommand command) {
        this.name = command.name();
        this.fuelType = command.fuelType();
        this.pricePerUnit = command.pricePerUnit();
        this.unit = command.unit();
        this.availableStock = command.availableStock();
        this.capacity = command.capacity();
        if (command.active() != null) {
            this.active = command.active();
        }
    }
}

