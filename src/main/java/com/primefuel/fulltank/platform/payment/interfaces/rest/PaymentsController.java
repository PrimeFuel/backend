package com.primefuel.fulltank.platform.payment.interfaces.rest;

import com.primefuel.fulltank.platform.payment.application.commandservices.PaymentCommandService;
import com.primefuel.fulltank.platform.payment.application.queryservices.PaymentQueryService;
import com.primefuel.fulltank.platform.payment.domain.model.commands.CompletePaymentCommand;
import com.primefuel.fulltank.platform.payment.domain.model.commands.RefundPaymentCommand;
import com.primefuel.fulltank.platform.payment.domain.model.queries.GetAllPaymentsQuery;
import com.primefuel.fulltank.platform.payment.domain.model.queries.GetPaymentByIdQuery;
import com.primefuel.fulltank.platform.payment.domain.model.queries.GetPaymentByOrderIdQuery;
import com.primefuel.fulltank.platform.payment.domain.model.queries.GetPaymentsByCompanyIdQuery;
import com.primefuel.fulltank.platform.payment.interfaces.rest.resources.CompletePaymentResource;
import com.primefuel.fulltank.platform.payment.interfaces.rest.resources.CreatePaymentResource;
import com.primefuel.fulltank.platform.payment.interfaces.rest.resources.PaymentResource;
import com.primefuel.fulltank.platform.payment.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.payment.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import com.primefuel.fulltank.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Payment management endpoints")
public class PaymentsController {

    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentsController(PaymentCommandService paymentCommandService,
                              PaymentQueryService paymentQueryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody CreatePaymentResource resource) {
        var command = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = paymentCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                PaymentResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }

    @PostMapping("/{paymentId}/complete")
    public ResponseEntity<?> completePayment(@PathVariable Long paymentId,
                                             @RequestBody CompletePaymentResource resource) {
        var result = paymentCommandService.handle(new CompletePaymentCommand(paymentId, resource.transactionReference()));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                PaymentResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @PostMapping("/{paymentId}/refund")
    public ResponseEntity<?> refundPayment(@PathVariable Long paymentId) {
        var result = paymentCommandService.handle(new RefundPaymentCommand(paymentId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                PaymentResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResource>> getAllPayments() {
        var payments = paymentQueryService.handle(new GetAllPaymentsQuery());
        var resources = payments.stream().map(PaymentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable Long paymentId) {
        var result = paymentQueryService.handle(new GetPaymentByIdQuery(paymentId));
        return result.map(p -> new ResponseEntity<>(
                        PaymentResourceFromEntityAssembler.toResourceFromEntity(p), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResource> getPaymentByOrder(@PathVariable Long orderId) {
        var result = paymentQueryService.handle(new GetPaymentByOrderIdQuery(orderId));
        return result.map(p -> new ResponseEntity<>(
                        PaymentResourceFromEntityAssembler.toResourceFromEntity(p), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<PaymentResource>> getPaymentsByCompany(@PathVariable Long companyId) {
        var payments = paymentQueryService.handle(new GetPaymentsByCompanyIdQuery(companyId));
        var resources = payments.stream().map(PaymentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
