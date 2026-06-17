package com.primefuel.fulltank.platform.equipment.interfaces.rest;

import com.primefuel.fulltank.platform.equipment.application.commandservices.EquipmentCommandService;
import com.primefuel.fulltank.platform.equipment.application.queryservices.EquipmentQueryService;
import com.primefuel.fulltank.platform.equipment.domain.model.queries.GetAllEquipmentQuery;
import com.primefuel.fulltank.platform.equipment.domain.model.queries.GetEquipmentByCompanyIdQuery;
import com.primefuel.fulltank.platform.equipment.domain.model.queries.GetEquipmentByIdQuery;
import com.primefuel.fulltank.platform.equipment.interfaces.rest.resources.CreateEquipmentResource;
import com.primefuel.fulltank.platform.equipment.interfaces.rest.resources.EquipmentResource;
import com.primefuel.fulltank.platform.equipment.interfaces.rest.resources.FavoriteProviderResource;
import com.primefuel.fulltank.platform.equipment.interfaces.rest.resources.UpdateEquipmentResource;
import com.primefuel.fulltank.platform.equipment.interfaces.rest.transform.CreateEquipmentCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.equipment.interfaces.rest.transform.EquipmentResourceFromEntityAssembler;
import com.primefuel.fulltank.platform.equipment.interfaces.rest.transform.UpdateEquipmentCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.primefuel.fulltank.platform.equipment.domain.repositories.EquipmentRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/equipment", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Equipment", description = "Equipment management endpoints")
public class EquipmentController {

    private final EquipmentCommandService equipmentCommandService;
    private final EquipmentQueryService equipmentQueryService;
    private final EquipmentRepository equipmentRepository;

    public EquipmentController(EquipmentCommandService equipmentCommandService,
                               EquipmentQueryService equipmentQueryService,
                               EquipmentRepository equipmentRepository) {
        this.equipmentCommandService = equipmentCommandService;
        this.equipmentQueryService = equipmentQueryService;
        this.equipmentRepository = equipmentRepository;
    }

    @PostMapping("/{equipmentId}/favorite-provider")
    public ResponseEntity<EquipmentResource> assignFavoriteProvider(
            @PathVariable Long equipmentId, @RequestBody FavoriteProviderResource resource) {
        return equipmentRepository.findById(equipmentId)
                .map(equipment -> {
                    equipment.assignFavoriteProvider(resource.providerId());
                    var saved = equipmentRepository.save(equipment);
                    return ResponseEntity.ok(EquipmentResourceFromEntityAssembler.toResourceFromEntity(saved));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createEquipment(@RequestBody CreateEquipmentResource resource) {
        var command = CreateEquipmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = equipmentCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                EquipmentResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }

    @PostMapping("/{equipmentId}/update")
    public ResponseEntity<?> updateEquipment(@PathVariable Long equipmentId,
                                             @RequestBody UpdateEquipmentResource resource) {
        var command = UpdateEquipmentCommandFromResourceAssembler.toCommandFromResource(equipmentId, resource);
        var result = equipmentCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                EquipmentResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EquipmentResource>> getAllEquipment() {
        var equipment = equipmentQueryService.handle(new GetAllEquipmentQuery());
        var resources = equipment.stream().map(EquipmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{equipmentId}")
    public ResponseEntity<EquipmentResource> getEquipmentById(@PathVariable Long equipmentId) {
        var result = equipmentQueryService.handle(new GetEquipmentByIdQuery(equipmentId));
        return result.map(e -> new ResponseEntity<>(
                        EquipmentResourceFromEntityAssembler.toResourceFromEntity(e), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<EquipmentResource>> getEquipmentByCompany(@PathVariable Long companyId) {
        var equipment = equipmentQueryService.handle(new GetEquipmentByCompanyIdQuery(companyId));
        var resources = equipment.stream().map(EquipmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
