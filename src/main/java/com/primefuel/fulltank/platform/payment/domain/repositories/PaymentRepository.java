package com.primefuel.fulltank.platform.payment.domain.repositories;

import com.primefuel.fulltank.platform.payment.domain.model.aggregates.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Optional<Payment> findById(Long id);
    Optional<Payment> findByOrderId(Long orderId);
    List<Payment> findAll();
    List<Payment> findByCompanyId(Long companyId);
    Payment save(Payment payment);
}
