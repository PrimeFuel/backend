package com.primefuel.fulltank.platform.fulfillment.interfaces.rest.transform;

import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.CreateDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.interfaces.rest.resources.CreateDeliveryResource;

public final class CreateDeliveryCommandFromResourceAssembler {

    private CreateDeliveryCommandFromResourceAssembler() {
    }

    public static CreateDeliveryCommand toCommandFromResource(CreateDeliveryResource resource) {
        return new CreateDeliveryCommand(resource.orderId(), resource.providerId(), resource.driverId(),
                resource.vehicleId(), resource.scheduledDate(), resource.notes());
    }
}
