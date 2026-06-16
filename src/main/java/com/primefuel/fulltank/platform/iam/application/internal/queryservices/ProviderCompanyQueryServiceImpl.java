package com.primefuel.fulltank.platform.iam.application.internal.queryservices;

import com.primefuel.fulltank.platform.iam.application.queryservices.ProviderCompanyQueryService;
import com.primefuel.fulltank.platform.iam.domain.model.aggregates.ProviderCompany;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetAllProviderCompaniesQuery;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetProviderCompanyByIdQuery;
import com.primefuel.fulltank.platform.iam.domain.repositories.ProviderCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderCompanyQueryServiceImpl implements ProviderCompanyQueryService {

    private final ProviderCompanyRepository providerCompanyRepository;

    public ProviderCompanyQueryServiceImpl(ProviderCompanyRepository providerCompanyRepository) {
        this.providerCompanyRepository = providerCompanyRepository;
    }

    @Override
    public Optional<ProviderCompany> handle(GetProviderCompanyByIdQuery query) {
        return providerCompanyRepository.findById(query.providerId());
    }

    @Override
    public List<ProviderCompany> handle(GetAllProviderCompaniesQuery query) {
        return providerCompanyRepository.findAll();
    }
}
