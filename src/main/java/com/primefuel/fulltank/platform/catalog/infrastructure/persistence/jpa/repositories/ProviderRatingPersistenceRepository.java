package com.primefuel.fulltank.platform.catalog.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.catalog.infrastructure.persistence.jpa.entities.ProviderRatingPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProviderRatingPersistenceRepository
        extends JpaRepository<ProviderRatingPersistenceEntity, Long> {
    Optional<ProviderRatingPersistenceEntity> findByCompanyIdAndProviderId(Long companyId, Long providerId);
    List<ProviderRatingPersistenceEntity> findByCompanyId(Long companyId);
    List<ProviderRatingPersistenceEntity> findByProviderId(Long providerId);
    List<ProviderRatingPersistenceEntity> findByCompanyIdAndProviderIdOrderById(
            Long companyId, Long providerId);
}
