package com.primefuel.fulltank.platform.inventory.interfaces.rest.transform;

import com.primefuel.fulltank.platform.inventory.domain.model.commands.UpdateFuelProductCommand;
import com.primefuel.fulltank.platform.inventory.interfaces.rest.resources.UpdateFuelProductResource;

public class UpdateFuelProductCommandFromResourceAssembler {
    public static UpdateFuelProductCommand toCommandFromResource(Long id, UpdateFuelProductResource resource) {
        return new UpdateFuelProductCommand(id, resource.name(), resource.fuelType(), resource.pricePerUnit(), resource.unit(),
                resource.availableStock(), resource.capacity(), resource.active());
    }
}
