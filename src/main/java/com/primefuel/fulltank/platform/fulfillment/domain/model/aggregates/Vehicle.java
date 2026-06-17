package com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates;

import com.primefuel.fulltank.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Vehicle extends AbstractDomainAggregateRoot<Vehicle> {

    private Long id;
    private Long providerId;
    private String licensePlate;
    private String brand;
    private String model;
    private Double capacity;
    private String unit;
    private String status;

    public Vehicle(Long providerId, String licensePlate, String brand, String model,
                   Double capacity, String unit, String status) {
        update(providerId, licensePlate, brand, model, capacity, unit, status);
    }

    public void update(Long providerId, String licensePlate, String brand, String model,
                       Double capacity, String unit, String status) {
        this.providerId = providerId;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.unit = unit;
        this.status = status;
    }
}
