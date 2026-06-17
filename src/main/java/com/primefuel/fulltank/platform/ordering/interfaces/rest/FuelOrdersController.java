package com.primefuel.fulltank.platform.ordering.interfaces.rest;

import com.primefuel.fulltank.platform.ordering.application.commandservices.FuelOrderCommandService;
import com.primefuel.fulltank.platform.ordering.application.queryservices.FuelOrderQueryService;
import com.primefuel.fulltank.platform.ordering.domain.model.commands.CancelFuelOrderCommand;
import com.primefuel.fulltank.platform.ordering.domain.model.commands.ConfirmFuelOrderCommand;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetAllFuelOrdersQuery;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetFuelOrderByIdQuery;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetFuelOrdersByCompanyIdQuery;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetFuelOrdersByProviderIdQuery;
import com.primefuel.fulltank.platform.ordering.interfaces.rest.resources.CreateFuelOrderResource;
import com.primefuel.fulltank.platform.ordering.interfaces.rest.resources.FuelOrderResource;
import com.primefuel.fulltank.platform.ordering.interfaces.rest.transform.CreateFuelOrderCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.ordering.interfaces.rest.transform.FuelOrderResourceFromEntityAssembler;
import com.primefuel.fulltank.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fuel-orders", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Fuel Orders", description = "Ordering management endpoints")
public class FuelOrdersController {

    private final FuelOrderCommandService fuelOrderCommandService;
    private final FuelOrderQueryService fuelOrderQueryService;

    public FuelOrdersController(FuelOrderCommandService fuelOrderCommandService,
                                FuelOrderQueryService fuelOrderQueryService) {
        this.fuelOrderCommandService = fuelOrderCommandService;
        this.fuelOrderQueryService = fuelOrderQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createFuelOrder(@RequestBody CreateFuelOrderResource resource) {
        var command = CreateFuelOrderCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = fuelOrderCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                FuelOrderResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }

    @PostMapping("/{orderId}/confirm")
    public ResponseEntity<?> confirmOrder(@PathVariable Long orderId) {
        var result = fuelOrderCommandService.handle(new ConfirmFuelOrderCommand(orderId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                FuelOrderResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        var result = fuelOrderCommandService.handle(new CancelFuelOrderCommand(orderId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                FuelOrderResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FuelOrderResource>> getAllOrders() {
        var orders = fuelOrderQueryService.handle(new GetAllFuelOrdersQuery());
        var resources = orders.stream().map(FuelOrderResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<FuelOrderResource> getOrderById(@PathVariable Long orderId) {
        var result = fuelOrderQueryService.handle(new GetFuelOrderByIdQuery(orderId));
        return result.map(o -> new ResponseEntity<>(
                        FuelOrderResourceFromEntityAssembler.toResourceFromEntity(o), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<FuelOrderResource>> getOrdersByCompany(@PathVariable Long companyId) {
        var orders = fuelOrderQueryService.handle(new GetFuelOrdersByCompanyIdQuery(companyId));
        var resources = orders.stream().map(FuelOrderResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<FuelOrderResource>> getOrdersByProvider(@PathVariable Long providerId) {
        var orders = fuelOrderQueryService.handle(new GetFuelOrdersByProviderIdQuery(providerId));
        var resources = orders.stream().map(FuelOrderResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
