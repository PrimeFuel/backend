package com.primefuel.fulltank.platform.iam.interfaces.rest;

import com.primefuel.fulltank.platform.iam.application.commandservices.BuyerCompanyCommandService;
import com.primefuel.fulltank.platform.iam.application.queryservices.BuyerCompanyQueryService;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetAllBuyerCompaniesQuery;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetBuyerCompanyByIdQuery;
import com.primefuel.fulltank.platform.iam.domain.repositories.BuyerCompanyRepository;
import com.primefuel.fulltank.platform.iam.interfaces.rest.resources.BuyerCompanyResource;
import com.primefuel.fulltank.platform.iam.interfaces.rest.resources.CreateBuyerCompanyResource;
import com.primefuel.fulltank.platform.iam.interfaces.rest.transform.BuyerCompanyResourceFromEntityAssembler;
import com.primefuel.fulltank.platform.iam.interfaces.rest.transform.CreateBuyerCompanyCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/buyer-companies", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Buyer Companies", description = "Buyer company management endpoints")
public class BuyerCompaniesController {

    private final BuyerCompanyCommandService buyerCompanyCommandService;
    private final BuyerCompanyQueryService buyerCompanyQueryService;
    private final BuyerCompanyRepository buyerCompanyRepository;

    public BuyerCompaniesController(BuyerCompanyCommandService buyerCompanyCommandService,
                                    BuyerCompanyQueryService buyerCompanyQueryService,
                                    BuyerCompanyRepository buyerCompanyRepository) {
        this.buyerCompanyCommandService = buyerCompanyCommandService;
        this.buyerCompanyQueryService = buyerCompanyQueryService;
        this.buyerCompanyRepository = buyerCompanyRepository;
    }

    @PostMapping
    public ResponseEntity<?> createBuyerCompany(@RequestBody CreateBuyerCompanyResource resource) {
        var command = CreateBuyerCompanyCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = buyerCompanyCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                BuyerCompanyResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BuyerCompanyResource>> getAllBuyerCompanies() {
        var companies = buyerCompanyQueryService.handle(new GetAllBuyerCompaniesQuery());
        var resources = companies.stream().map(BuyerCompanyResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<BuyerCompanyResource> getBuyerCompanyById(@PathVariable Long companyId) {
        var result = buyerCompanyQueryService.handle(new GetBuyerCompanyByIdQuery(companyId));
        return result.map(company -> new ResponseEntity<>(
                        BuyerCompanyResourceFromEntityAssembler.toResourceFromEntity(company), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<BuyerCompanyResource> updateBuyerCompany(@PathVariable Long companyId,
                                                                   @RequestBody CreateBuyerCompanyResource resource) {
        var result = buyerCompanyRepository.findById(companyId);
        if (result.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var company = result.get();
        company.setName(resource.name());
        company.setRuc(resource.ruc());
        company.setSector(resource.sector());
        company.setAddress(resource.address());
        company.setContactEmail(resource.contactEmail());
        company.setPhone(resource.phone());
        var updated = buyerCompanyRepository.save(company);
        return new ResponseEntity<>(BuyerCompanyResourceFromEntityAssembler.toResourceFromEntity(updated), HttpStatus.OK);
    }
}
