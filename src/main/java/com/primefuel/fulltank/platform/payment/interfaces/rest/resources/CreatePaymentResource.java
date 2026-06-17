package com.primefuel.fulltank.platform.payment.interfaces.rest.resources;

import com.primefuel.fulltank.platform.payment.domain.model.valueobjects.PaymentMethod;

public record CreatePaymentResource(Long orderId, Long companyId, Double amount, PaymentMethod paymentMethod) {
}
