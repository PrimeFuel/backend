package com.primefuel.fulltank.platform.payment.application.queryservices;

import com.primefuel.fulltank.platform.payment.domain.model.aggregates.Payment;
import com.primefuel.fulltank.platform.payment.domain.model.queries.GetAllPaymentsQuery;
import com.primefuel.fulltank.platform.payment.domain.model.queries.GetPaymentByIdQuery;
import com.primefuel.fulltank.platform.payment.domain.model.queries.GetPaymentByOrderIdQuery;
import com.primefuel.fulltank.platform.payment.domain.model.queries.GetPaymentsByCompanyIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByIdQuery query);
    Optional<Payment> handle(GetPaymentByOrderIdQuery query);
    List<Payment> handle(GetAllPaymentsQuery query);
    List<Payment> handle(GetPaymentsByCompanyIdQuery query);
}
