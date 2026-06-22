package com.primefuel.fulltank.platform.iam.interfaces.rest.transform;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.ProviderCompany;
import com.primefuel.fulltank.platform.iam.interfaces.rest.resources.ProviderCompanyResource;

public final class ProviderCompanyResourceFromEntityAssembler {

    private ProviderCompanyResourceFromEntityAssembler() {
    }

    public static ProviderCompanyResource toResourceFromEntity(ProviderCompany providerCompany) {
        return new ProviderCompanyResource(providerCompany.getId(), providerCompany.getName(),
                providerCompany.getRuc(), providerCompany.getRating(), providerCompany.getAddress(),
                providerCompany.getPhone(), providerCompany.getFuelTypesOffered(),
                providerCompany.getDescription());
    }
}
