package com.primefuel.fulltank.platform.inventory.interfaces.rest;

import com.primefuel.fulltank.platform.inventory.application.commandservices.FuelProductCommandService;
import com.primefuel.fulltank.platform.inventory.application.queryservices.FuelProductQueryService;
import com.primefuel.fulltank.platform.inventory.domain.model.commands.DeleteFuelProductCommand;
import com.primefuel.fulltank.platform.inventory.domain.model.queries.GetAllFuelProductsQuery;
import com.primefuel.fulltank.platform.inventory.domain.model.queries.GetFuelProductByIdQuery;
import com.primefuel.fulltank.platform.inventory.domain.model.queries.GetFuelProductsByProviderIdQuery;
import com.primefuel.fulltank.platform.inventory.interfaces.rest.resources.CreateFuelProductResource;
import com.primefuel.fulltank.platform.inventory.interfaces.rest.resources.FuelProductResource;
import com.primefuel.fulltank.platform.inventory.interfaces.rest.resources.UpdateFuelProductResource;
import com.primefuel.fulltank.platform.inventory.interfaces.rest.resources.UpdateFuelProductStockResource;
import com.primefuel.fulltank.platform.inventory.interfaces.rest.transform.CreateFuelProductCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.inventory.interfaces.rest.transform.FuelProductResourceFromEntityAssembler;
import com.primefuel.fulltank.platform.inventory.interfaces.rest.transform.UpdateFuelProductCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.inventory.interfaces.rest.transform.UpdateFuelProductStockCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fuel-products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Fuel Products", description = "Inventory management endpoints")
public class FuelProductsController {

    private final FuelProductCommandService fuelProductCommandService;
    private final FuelProductQueryService fuelProductQueryService;

    public FuelProductsController(FuelProductCommandService fuelProductCommandService,
                                  FuelProductQueryService fuelProductQueryService) {
        this.fuelProductCommandService = fuelProductCommandService;
        this.fuelProductQueryService = fuelProductQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createFuelProduct(@RequestBody CreateFuelProductResource resource) {
        var command = CreateFuelProductCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = fuelProductCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                FuelProductResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }

    @PostMapping("/{fuelProductId}/update-stock")
    public ResponseEntity<?> updateStock(@PathVariable Long fuelProductId,
                                         @RequestBody UpdateFuelProductStockResource resource) {
        var command = UpdateFuelProductStockCommandFromResourceAssembler.toCommandFromResource(fuelProductId, resource);
        var result = fuelProductCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                FuelProductResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FuelProductResource>> getAllFuelProducts() {
        var products = fuelProductQueryService.handle(new GetAllFuelProductsQuery());
        var resources = products.stream().map(FuelProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{fuelProductId}")
    public ResponseEntity<FuelProductResource> getFuelProductById(@PathVariable Long fuelProductId) {
        var result = fuelProductQueryService.handle(new GetFuelProductByIdQuery(fuelProductId));
        return result.map(p -> new ResponseEntity<>(
                        FuelProductResourceFromEntityAssembler.toResourceFromEntity(p), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<FuelProductResource>> getFuelProductsByProvider(@PathVariable Long providerId) {
        var products = fuelProductQueryService.handle(new GetFuelProductsByProviderIdQuery(providerId));
        var resources = products.stream().map(FuelProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @PutMapping("/{fuelProductId}")
    public ResponseEntity<?> updateFuelProduct(@PathVariable Long fuelProductId,
                                               @RequestBody UpdateFuelProductResource resource) {
        var command = UpdateFuelProductCommandFromResourceAssembler.toCommandFromResource(fuelProductId, resource);
        var result = fuelProductCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                FuelProductResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @DeleteMapping("/{fuelProductId}")
    public ResponseEntity<?> deleteFuelProduct(@PathVariable Long fuelProductId) {
        var result = fuelProductCommandService.handle(new DeleteFuelProductCommand(fuelProductId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ignored -> null,
                HttpStatus.NO_CONTENT);
    }
}
