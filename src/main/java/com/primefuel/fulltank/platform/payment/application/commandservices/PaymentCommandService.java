package com.primefuel.fulltank.platform.payment.application.commandservices;

import com.primefuel.fulltank.platform.payment.domain.model.aggregates.Payment;
import com.primefuel.fulltank.platform.payment.domain.model.commands.CompletePaymentCommand;
import com.primefuel.fulltank.platform.payment.domain.model.commands.CreatePaymentCommand;
import com.primefuel.fulltank.platform.payment.domain.model.commands.RefundPaymentCommand;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;

public interface PaymentCommandService {
    Result<Payment, ApplicationError> handle(CreatePaymentCommand command);
    Result<Payment, ApplicationError> handle(CompletePaymentCommand command);
    Result<Payment, ApplicationError> handle(RefundPaymentCommand command);
}
