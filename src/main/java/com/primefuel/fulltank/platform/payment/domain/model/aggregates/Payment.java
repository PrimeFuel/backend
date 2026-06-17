package com.primefuel.fulltank.platform.payment.domain.model.aggregates;

import com.primefuel.fulltank.platform.payment.domain.model.commands.CreatePaymentCommand;
import com.primefuel.fulltank.platform.payment.domain.model.valueobjects.PaymentMethod;
import com.primefuel.fulltank.platform.payment.domain.model.valueobjects.PaymentStatus;
import com.primefuel.fulltank.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Payment extends AbstractDomainAggregateRoot<Payment> {

    private Long id;
    private Long orderId;
    private Long companyId;
    private Double amount;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;
    private String transactionReference;
    private LocalDateTime paidAt;

    public Payment(CreatePaymentCommand command) {
        this.orderId = command.orderId();
        this.companyId = command.companyId();
        this.amount = command.amount();
        this.paymentMethod = command.paymentMethod();
        this.status = PaymentStatus.PENDING;
    }

    public void complete(String transactionReference) {
        this.status = PaymentStatus.COMPLETED;
        this.transactionReference = transactionReference;
        this.paidAt = LocalDateTime.now();
    }

    public void refund() {
        this.status = PaymentStatus.REFUNDED;
    }

    public void fail() {
        this.status = PaymentStatus.FAILED;
    }
}
