package com.primefuel.fulltank.platform.payment.interfaces.rest.transform;

import com.primefuel.fulltank.platform.payment.domain.model.commands.CreatePaymentCommand;
import com.primefuel.fulltank.platform.payment.interfaces.rest.resources.CreatePaymentResource;

public final class CreatePaymentCommandFromResourceAssembler {

    private CreatePaymentCommandFromResourceAssembler() {
    }

    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(resource.orderId(), resource.companyId(),
                resource.amount(), resource.paymentMethod());
    }
}
