package com.primefuel.fulltank.platform.payment.domain.model.commands;

import com.primefuel.fulltank.platform.payment.domain.model.valueobjects.PaymentMethod;

public record CreatePaymentCommand(Long orderId, Long companyId, Double amount, PaymentMethod paymentMethod) {
}
