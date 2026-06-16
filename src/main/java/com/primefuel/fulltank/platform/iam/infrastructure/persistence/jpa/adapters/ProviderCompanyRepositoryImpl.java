package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.ProviderCompany;
import com.primefuel.fulltank.platform.iam.domain.repositories.ProviderCompanyRepository;
import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.assemblers.ProviderCompanyPersistenceAssembler;
import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.repositories.ProviderCompanyPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProviderCompanyRepositoryImpl implements ProviderCompanyRepository {

    private final ProviderCompanyPersistenceRepository providerCompanyRepository;

    public ProviderCompanyRepositoryImpl(ProviderCompanyPersistenceRepository providerCompanyRepository) {
        this.providerCompanyRepository = providerCompanyRepository;
    }

    @Override
    public Optional<ProviderCompany> findById(Long id) {
        return providerCompanyRepository.findById(id)
                .map(ProviderCompanyPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<ProviderCompany> findAll() {
        return providerCompanyRepository.findAll().stream()
                .map(ProviderCompanyPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public ProviderCompany save(ProviderCompany providerCompany) {
        var entity = ProviderCompanyPersistenceAssembler.toPersistenceFromDomain(providerCompany);
        return ProviderCompanyPersistenceAssembler.toDomainFromPersistence(providerCompanyRepository.save(entity));
    }
}
