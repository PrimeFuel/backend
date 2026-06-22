package com.primefuel.fulltank.platform.iam.interfaces.rest.transform;

import com.primefuel.fulltank.platform.iam.domain.model.commands.CreateProviderCompanyCommand;
import com.primefuel.fulltank.platform.iam.interfaces.rest.resources.CreateProviderCompanyResource;

public final class CreateProviderCompanyCommandFromResourceAssembler {

    private CreateProviderCompanyCommandFromResourceAssembler() {
    }

    public static CreateProviderCompanyCommand toCommandFromResource(CreateProviderCompanyResource resource) {
        return new CreateProviderCompanyCommand(resource.name(), resource.ruc(), resource.rating(),
                resource.address(), resource.phone(), resource.fuelTypesOffered(), resource.description());
    }
}
