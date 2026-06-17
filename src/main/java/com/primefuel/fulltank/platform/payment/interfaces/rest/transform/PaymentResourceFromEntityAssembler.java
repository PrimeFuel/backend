package com.primefuel.fulltank.platform.payment.interfaces.rest.transform;

import com.primefuel.fulltank.platform.payment.domain.model.aggregates.Payment;
import com.primefuel.fulltank.platform.payment.interfaces.rest.resources.PaymentResource;

public final class PaymentResourceFromEntityAssembler {

    private PaymentResourceFromEntityAssembler() {
    }

    public static PaymentResource toResourceFromEntity(Payment payment) {
        return new PaymentResource(payment.getId(), payment.getOrderId(), payment.getCompanyId(),
                payment.getAmount(), payment.getStatus(), payment.getPaymentMethod(),
                payment.getTransactionReference(), payment.getPaidAt());
    }
}
