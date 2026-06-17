package com.primefuel.fulltank.platform.catalog.domain.repositories;

import com.primefuel.fulltank.platform.catalog.domain.model.aggregates.ProviderRating;

import java.util.List;
import java.util.Optional;

public interface ProviderRatingRepository {
    Optional<ProviderRating> findById(Long id);
    Optional<ProviderRating> findByCompanyIdAndProviderId(Long companyId, Long providerId);
    List<ProviderRating> findAll(Long companyId, Long providerId);
    ProviderRating save(ProviderRating rating);
}
