package com.primefuel.fulltank.platform.payment.infrastructure.persistence.jpa.entities;

import com.primefuel.fulltank.platform.payment.domain.model.valueobjects.PaymentMethod;
import com.primefuel.fulltank.platform.payment.domain.model.valueobjects.PaymentStatus;
import com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
public class PaymentPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    private String transactionReference;

    private LocalDateTime paidAt;
}
