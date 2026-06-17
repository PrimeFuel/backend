package com.primefuel.fulltank.platform.fulfillment.interfaces.rest;

import com.primefuel.fulltank.platform.fulfillment.application.commandservices.DeliveryCommandService;
import com.primefuel.fulltank.platform.fulfillment.application.queryservices.DeliveryQueryService;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.CompleteDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.DispatchDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.FailDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.domain.model.queries.GetAllDeliveriesQuery;
import com.primefuel.fulltank.platform.fulfillment.domain.model.queries.GetDeliveryByIdQuery;
import com.primefuel.fulltank.platform.fulfillment.domain.model.queries.GetDeliveryByOrderIdQuery;
import com.primefuel.fulltank.platform.fulfillment.interfaces.rest.resources.CreateDeliveryResource;
import com.primefuel.fulltank.platform.fulfillment.interfaces.rest.resources.DeliveryResource;
import com.primefuel.fulltank.platform.fulfillment.interfaces.rest.resources.FailDeliveryResource;
import com.primefuel.fulltank.platform.fulfillment.interfaces.rest.transform.CreateDeliveryCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.fulfillment.interfaces.rest.transform.DeliveryResourceFromEntityAssembler;
import com.primefuel.fulltank.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/deliveries", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Deliveries", description = "Fulfillment management endpoints")
public class DeliveriesController {

    private final DeliveryCommandService deliveryCommandService;
    private final DeliveryQueryService deliveryQueryService;

    public DeliveriesController(DeliveryCommandService deliveryCommandService,
                                DeliveryQueryService deliveryQueryService) {
        this.deliveryCommandService = deliveryCommandService;
        this.deliveryQueryService = deliveryQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createDelivery(@RequestBody CreateDeliveryResource resource) {
        var command = CreateDeliveryCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = deliveryCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DeliveryResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }

    @PostMapping("/{deliveryId}/dispatch")
    public ResponseEntity<?> dispatchDelivery(@PathVariable Long deliveryId) {
        var result = deliveryCommandService.handle(new DispatchDeliveryCommand(deliveryId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DeliveryResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @PostMapping("/{deliveryId}/complete")
    public ResponseEntity<?> completeDelivery(@PathVariable Long deliveryId) {
        var result = deliveryCommandService.handle(new CompleteDeliveryCommand(deliveryId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DeliveryResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @PostMapping("/{deliveryId}/fail")
    public ResponseEntity<?> failDelivery(@PathVariable Long deliveryId,
                                          @RequestBody FailDeliveryResource resource) {
        var result = deliveryCommandService.handle(new FailDeliveryCommand(deliveryId, resource.reason()));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DeliveryResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryResource>> getAllDeliveries() {
        var deliveries = deliveryQueryService.handle(new GetAllDeliveriesQuery());
        var resources = deliveries.stream().map(DeliveryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<DeliveryResource>> getDeliveriesByProvider(@PathVariable Long providerId) {
        var resources = deliveryQueryService.handle(new GetAllDeliveriesQuery()).stream()
                .filter(delivery -> providerId.equals(delivery.getProviderId()))
                .map(DeliveryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryResource> getDeliveryById(@PathVariable Long deliveryId) {
        var result = deliveryQueryService.handle(new GetDeliveryByIdQuery(deliveryId));
        return result.map(d -> new ResponseEntity<>(
                        DeliveryResourceFromEntityAssembler.toResourceFromEntity(d), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<DeliveryResource> getDeliveryByOrder(@PathVariable Long orderId) {
        var result = deliveryQueryService.handle(new GetDeliveryByOrderIdQuery(orderId));
        return result.map(d -> new ResponseEntity<>(
                        DeliveryResourceFromEntityAssembler.toResourceFromEntity(d), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
