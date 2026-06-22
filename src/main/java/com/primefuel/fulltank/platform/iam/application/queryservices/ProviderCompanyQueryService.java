package com.primefuel.fulltank.platform.iam.application.queryservices;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.ProviderCompany;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetAllProviderCompaniesQuery;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetProviderCompanyByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProviderCompanyQueryService {
    Optional<ProviderCompany> handle(GetProviderCompanyByIdQuery query);
    List<ProviderCompany> handle(GetAllProviderCompaniesQuery query);
}
