package com.primefuel.fulltank.platform.iam.interfaces.rest;

import com.primefuel.fulltank.platform.iam.application.commandservices.ProviderCompanyCommandService;
import com.primefuel.fulltank.platform.iam.application.queryservices.ProviderCompanyQueryService;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetAllProviderCompaniesQuery;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetProviderCompanyByIdQuery;
import com.primefuel.fulltank.platform.iam.domain.repositories.ProviderCompanyRepository;
import com.primefuel.fulltank.platform.iam.interfaces.rest.resources.CreateProviderCompanyResource;
import com.primefuel.fulltank.platform.iam.interfaces.rest.resources.ProviderCompanyResource;
import com.primefuel.fulltank.platform.iam.interfaces.rest.transform.CreateProviderCompanyCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.iam.interfaces.rest.transform.ProviderCompanyResourceFromEntityAssembler;
import com.primefuel.fulltank.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/provider-companies", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Provider Companies", description = "Provider company management endpoints")
public class ProviderCompaniesController {

    private final ProviderCompanyCommandService providerCompanyCommandService;
    private final ProviderCompanyQueryService providerCompanyQueryService;
    private final ProviderCompanyRepository providerCompanyRepository;

    public ProviderCompaniesController(ProviderCompanyCommandService providerCompanyCommandService,
                                       ProviderCompanyQueryService providerCompanyQueryService,
                                       ProviderCompanyRepository providerCompanyRepository) {
        this.providerCompanyCommandService = providerCompanyCommandService;
        this.providerCompanyQueryService = providerCompanyQueryService;
        this.providerCompanyRepository = providerCompanyRepository;
    }

    @PostMapping
    public ResponseEntity<?> createProviderCompany(@RequestBody CreateProviderCompanyResource resource) {
        var command = CreateProviderCompanyCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = providerCompanyCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ProviderCompanyResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProviderCompanyResource>> getAllProviderCompanies() {
        var companies = providerCompanyQueryService.handle(new GetAllProviderCompaniesQuery());
        var resources = companies.stream().map(ProviderCompanyResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{providerId}")
    public ResponseEntity<ProviderCompanyResource> getProviderCompanyById(@PathVariable Long providerId) {
        var result = providerCompanyQueryService.handle(new GetProviderCompanyByIdQuery(providerId));
        return result.map(company -> new ResponseEntity<>(
                        ProviderCompanyResourceFromEntityAssembler.toResourceFromEntity(company), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{providerId}")
    public ResponseEntity<ProviderCompanyResource> updateProviderCompany(@PathVariable Long providerId,
                                                                         @RequestBody CreateProviderCompanyResource resource) {
        var result = providerCompanyRepository.findById(providerId);
        if (result.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var provider = result.get();
        provider.setName(resource.name());
        provider.setRuc(resource.ruc());
        provider.setRating(resource.rating());
        provider.setAddress(resource.address());
        provider.setPhone(resource.phone());
        provider.setFuelTypesOffered(resource.fuelTypesOffered());
        provider.setDescription(resource.description());
        var updated = providerCompanyRepository.save(provider);
        return new ResponseEntity<>(ProviderCompanyResourceFromEntityAssembler.toResourceFromEntity(updated), HttpStatus.OK);
    }
}
