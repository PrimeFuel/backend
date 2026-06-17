package com.primefuel.fulltank.platform.payment.domain.model.commands;

public record CompletePaymentCommand(Long paymentId, String transactionReference) {
}
