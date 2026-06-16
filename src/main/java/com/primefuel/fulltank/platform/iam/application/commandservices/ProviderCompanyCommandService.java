package com.primefuel.fulltank.platform.iam.application.commandservices;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.ProviderCompany;
import com.primefuel.fulltank.platform.iam.domain.model.commands.CreateProviderCompanyCommand;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;

public interface ProviderCompanyCommandService {
    Result<ProviderCompany, ApplicationError> handle(CreateProviderCompanyCommand command);
}
