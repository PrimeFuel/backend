package com.primefuel.fulltank.platform.catalog.interfaces.rest;

import com.primefuel.fulltank.platform.catalog.domain.model.aggregates.ProviderRating;
import com.primefuel.fulltank.platform.catalog.domain.repositories.ProviderRatingRepository;
import com.primefuel.fulltank.platform.catalog.interfaces.rest.resources.ProviderRatingResource;
import com.primefuel.fulltank.platform.iam.domain.repositories.BuyerCompanyRepository;
import com.primefuel.fulltank.platform.iam.domain.repositories.ProviderCompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provider-ratings")
public class ProviderRatingsController {

    private final ProviderRatingRepository ratingRepository;
    private final BuyerCompanyRepository buyerCompanyRepository;
    private final ProviderCompanyRepository providerCompanyRepository;

    public ProviderRatingsController(ProviderRatingRepository ratingRepository,
                                     BuyerCompanyRepository buyerCompanyRepository,
                                     ProviderCompanyRepository providerCompanyRepository) {
        this.ratingRepository = ratingRepository;
        this.buyerCompanyRepository = buyerCompanyRepository;
        this.providerCompanyRepository = providerCompanyRepository;
    }

    @GetMapping
    public List<ProviderRatingResource> getAll(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long providerId) {
        return ratingRepository.findAll(companyId, providerId).stream()
                .map(ProviderRatingsController::toResource).toList();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProviderRatingResource resource) {
        var validation = validate(resource);
        if (validation != null) return validation;
        if (ratingRepository.findByCompanyIdAndProviderId(resource.companyId(), resource.providerId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("The buyer company already rated this provider");
        }
        try {
            var saved = ratingRepository.save(
                    new ProviderRating(resource.companyId(), resource.providerId(), resource.rating()));
            return new ResponseEntity<>(toResource(saved), HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProviderRatingResource resource) {
        var validation = validate(resource);
        if (validation != null) return validation;
        var rating = ratingRepository.findById(id).orElse(null);
        if (rating == null) return ResponseEntity.notFound().build();
        if (!rating.getCompanyId().equals(resource.companyId())
                || !rating.getProviderId().equals(resource.providerId())) {
            return ResponseEntity.badRequest().body("Company and provider cannot be changed");
        }
        try {
            rating.changeRating(resource.rating());
            return ResponseEntity.ok(toResource(ratingRepository.save(rating)));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    private ResponseEntity<?> validate(ProviderRatingResource resource) {
        if (resource.companyId() == null || resource.providerId() == null
                || resource.rating() == null || resource.rating() < 1 || resource.rating() > 5) {
            return ResponseEntity.badRequest().body("A valid company, provider and rating from 1 to 5 are required");
        }
        if (buyerCompanyRepository.findById(resource.companyId()).isEmpty()
                || providerCompanyRepository.findById(resource.providerId()).isEmpty()) {
            return ResponseEntity.badRequest().body("Buyer company or provider does not exist");
        }
        return null;
    }

    private static ProviderRatingResource toResource(ProviderRating rating) {
        return new ProviderRatingResource(rating.getId(), rating.getCompanyId(),
                rating.getProviderId(), rating.getRating());
    }
}
