package com.primefuel.fulltank.platform.payment.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.payment.domain.model.aggregates.Payment;
import com.primefuel.fulltank.platform.payment.domain.repositories.PaymentRepository;
import com.primefuel.fulltank.platform.payment.infrastructure.persistence.jpa.assemblers.PaymentPersistenceAssembler;
import com.primefuel.fulltank.platform.payment.infrastructure.persistence.jpa.repositories.PaymentPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentPersistenceRepository paymentPersistenceRepository;

    public PaymentRepositoryImpl(PaymentPersistenceRepository paymentPersistenceRepository) {
        this.paymentPersistenceRepository = paymentPersistenceRepository;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentPersistenceRepository.findById(id)
                .map(PaymentPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<Payment> findByOrderId(Long orderId) {
        return paymentPersistenceRepository.findByOrderId(orderId)
                .map(PaymentPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Payment> findAll() {
        return paymentPersistenceRepository.findAll().stream()
                .map(PaymentPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<Payment> findByCompanyId(Long companyId) {
        return paymentPersistenceRepository.findByCompanyId(companyId).stream()
                .map(PaymentPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Payment save(Payment payment) {
        var entity = PaymentPersistenceAssembler.toPersistenceFromDomain(payment);
        return PaymentPersistenceAssembler.toDomainFromPersistence(
                paymentPersistenceRepository.save(entity));
    }
}
