package com.primefuel.fulltank.platform.iam.domain.repositories;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.ProviderCompany;

import java.util.List;
import java.util.Optional;

public interface ProviderCompanyRepository {
    Optional<ProviderCompany> findById(Long id);
    List<ProviderCompany> findAll();
    ProviderCompany save(ProviderCompany providerCompany);
}
