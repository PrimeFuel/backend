package com.primefuel.fulltank.platform.iam.application.commandservices;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.BuyerCompany;
import com.primefuel.fulltank.platform.iam.domain.model.commands.CreateBuyerCompanyCommand;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;

public interface BuyerCompanyCommandService {
    Result<BuyerCompany, ApplicationError> handle(CreateBuyerCompanyCommand command);
}
