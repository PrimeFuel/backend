package com.primefuel.fulltank.platform.ordering.interfaces.rest;

import com.primefuel.fulltank.platform.ordering.application.internal.commandservices.FuelRequestService;
import com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.entities.FuelRequestPersistenceEntity;
import com.primefuel.fulltank.platform.ordering.interfaces.rest.resources.*;
import com.primefuel.fulltank.platform.ordering.interfaces.rest.transform.FuelOrderResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fuel-requests")
public class FuelRequestsController {
    private final FuelRequestService service;

    public FuelRequestsController(FuelRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FuelRequestResource> create(@RequestBody CreateFuelRequestResource resource) {
        return new ResponseEntity<>(toResource(service.create(resource)), HttpStatus.CREATED);
    }

    @GetMapping
    public List<FuelRequestResource> findAll(@RequestParam(required = false) Long buyerCompanyId,
                                             @RequestParam(required = false) Long providerId) {
        return service.findAll(buyerCompanyId, providerId).stream().map(FuelRequestsController::toResource).toList();
    }

    @PostMapping("/{requestId}/accept")
    public ResponseEntity<?> accept(@PathVariable Long requestId) {
        return ResponseEntity.ok(FuelOrderResourceFromEntityAssembler.toResourceFromEntity(service.accept(requestId)));
    }

    @PostMapping("/{requestId}/reject")
    public ResponseEntity<FuelRequestResource> reject(@PathVariable Long requestId,
                                                      @RequestBody RejectFuelRequestResource resource) {
        return ResponseEntity.ok(toResource(service.reject(requestId, resource.reason())));
    }

    private static FuelRequestResource toResource(FuelRequestPersistenceEntity r) {
        return new FuelRequestResource(r.getId(), r.getBuyerCompanyId(), r.getProviderId(), r.getEquipmentId(),
                r.getFuelProductId(), r.getFuelType(), r.getProductName(), r.getQuantity(), r.getUnit(),
                r.getUnitPrice(), r.getDeliveryAddress(), r.getDeliveryDate(), r.getStatus(), r.getSource(),
                r.getRejectionReason(), r.getCreatedAt(), r.getUpdatedAt());
    }
}
