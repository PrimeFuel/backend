package com.primefuel.fulltank.platform.catalog.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.catalog.domain.model.aggregates.ProviderRating;
import com.primefuel.fulltank.platform.catalog.domain.repositories.ProviderRatingRepository;
import com.primefuel.fulltank.platform.catalog.infrastructure.persistence.jpa.entities.ProviderRatingPersistenceEntity;
import com.primefuel.fulltank.platform.catalog.infrastructure.persistence.jpa.repositories.ProviderRatingPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProviderRatingRepositoryImpl implements ProviderRatingRepository {

    private final ProviderRatingPersistenceRepository persistenceRepository;

    public ProviderRatingRepositoryImpl(ProviderRatingPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<ProviderRating> findById(Long id) {
        return persistenceRepository.findById(id).map(ProviderRatingRepositoryImpl::toDomain);
    }

    @Override
    public Optional<ProviderRating> findByCompanyIdAndProviderId(Long companyId, Long providerId) {
        return persistenceRepository.findByCompanyIdAndProviderId(companyId, providerId)
                .map(ProviderRatingRepositoryImpl::toDomain);
    }

    @Override
    public List<ProviderRating> findAll(Long companyId, Long providerId) {
        List<ProviderRatingPersistenceEntity> rows;
        if (companyId != null && providerId != null) {
            rows = persistenceRepository.findByCompanyIdAndProviderIdOrderById(companyId, providerId);
        } else if (companyId != null) {
            rows = persistenceRepository.findByCompanyId(companyId);
        } else if (providerId != null) {
            rows = persistenceRepository.findByProviderId(providerId);
        } else {
            rows = persistenceRepository.findAll();
        }
        return rows.stream().map(ProviderRatingRepositoryImpl::toDomain).toList();
    }

    @Override
    public ProviderRating save(ProviderRating rating) {
        var entity = new ProviderRatingPersistenceEntity();
        if (rating.getId() != null) entity.setId(rating.getId());
        entity.setCompanyId(rating.getCompanyId());
        entity.setProviderId(rating.getProviderId());
        entity.setRating(rating.getRating());
        return toDomain(persistenceRepository.save(entity));
    }

    private static ProviderRating toDomain(ProviderRatingPersistenceEntity entity) {
        var rating = new ProviderRating();
        rating.setId(entity.getId());
        rating.setCompanyId(entity.getCompanyId());
        rating.setProviderId(entity.getProviderId());
        rating.setRating(entity.getRating());
        return rating;
    }
}
