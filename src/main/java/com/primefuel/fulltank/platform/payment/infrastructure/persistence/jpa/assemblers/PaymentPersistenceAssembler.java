package com.primefuel.fulltank.platform.payment.infrastructure.persistence.jpa.assemblers;

import com.primefuel.fulltank.platform.payment.domain.model.aggregates.Payment;
import com.primefuel.fulltank.platform.payment.infrastructure.persistence.jpa.entities.PaymentPersistenceEntity;

public final class PaymentPersistenceAssembler {

    private PaymentPersistenceAssembler() {
    }

    public static Payment toDomainFromPersistence(PaymentPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new Payment();
        domain.setId(entity.getId());
        domain.setOrderId(entity.getOrderId());
        domain.setCompanyId(entity.getCompanyId());
        domain.setAmount(entity.getAmount());
        domain.setStatus(entity.getStatus());
        domain.setPaymentMethod(entity.getPaymentMethod());
        domain.setTransactionReference(entity.getTransactionReference());
        domain.setPaidAt(entity.getPaidAt());
        return domain;
    }

    public static PaymentPersistenceEntity toPersistenceFromDomain(Payment domain) {
        if (domain == null) return null;
        var entity = new PaymentPersistenceEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setOrderId(domain.getOrderId());
        entity.setCompanyId(domain.getCompanyId());
        entity.setAmount(domain.getAmount());
        entity.setStatus(domain.getStatus());
        entity.setPaymentMethod(domain.getPaymentMethod());
        entity.setTransactionReference(domain.getTransactionReference());
        entity.setPaidAt(domain.getPaidAt());
        return entity;
    }
}
