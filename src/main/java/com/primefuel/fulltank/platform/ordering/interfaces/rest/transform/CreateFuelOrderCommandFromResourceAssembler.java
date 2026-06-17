package com.primefuel.fulltank.platform.ordering.interfaces.rest.transform;

import com.primefuel.fulltank.platform.ordering.domain.model.commands.CreateFuelOrderCommand;
import com.primefuel.fulltank.platform.ordering.interfaces.rest.resources.CreateFuelOrderResource;

public final class CreateFuelOrderCommandFromResourceAssembler {

    private CreateFuelOrderCommandFromResourceAssembler() {
    }

    public static CreateFuelOrderCommand toCommandFromResource(CreateFuelOrderResource resource) {
        return new CreateFuelOrderCommand(resource.companyId(), resource.providerId(),
                resource.fuelProductId(), resource.equipmentId(), resource.requestedQuantity(),
                resource.deliveryAddress(), resource.scheduledDate());
    }
}
