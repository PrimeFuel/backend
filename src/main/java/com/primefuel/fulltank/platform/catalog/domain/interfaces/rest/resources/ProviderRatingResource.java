package com.primefuel.fulltank.platform.catalog.domain.interfaces.rest.resources;

public record ProviderRatingResource(Long id, Long companyId, Long providerId, Integer rating) {
}
