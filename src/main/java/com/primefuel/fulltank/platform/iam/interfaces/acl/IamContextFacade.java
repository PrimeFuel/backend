package com.primefuel.fulltank.platform.iam.interfaces.acl;

import com.primefuel.fulltank.platform.iam.application.queryservices.BuyerCompanyQueryService;
import com.primefuel.fulltank.platform.iam.application.queryservices.ProviderCompanyQueryService;
import com.primefuel.fulltank.platform.iam.application.queryservices.UserQueryService;
import com.primefuel.fulltank.platform.iam.domain.model.aggregates.BuyerCompany;
import com.primefuel.fulltank.platform.iam.domain.model.aggregates.ProviderCompany;
import com.primefuel.fulltank.platform.iam.domain.model.aggregates.User;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetBuyerCompanyByIdQuery;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetProviderCompanyByIdQuery;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetUserByIdQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IamContextFacade {

    private final UserQueryService userQueryService;
    private final BuyerCompanyQueryService buyerCompanyQueryService;
    private final ProviderCompanyQueryService providerCompanyQueryService;

    public IamContextFacade(UserQueryService userQueryService,
                            BuyerCompanyQueryService buyerCompanyQueryService,
                            ProviderCompanyQueryService providerCompanyQueryService) {
        this.userQueryService = userQueryService;
        this.buyerCompanyQueryService = buyerCompanyQueryService;
        this.providerCompanyQueryService = providerCompanyQueryService;
    }

    public Optional<User> fetchUserById(Long userId) {
        return userQueryService.handle(new GetUserByIdQuery(userId));
    }

    public Optional<BuyerCompany> fetchBuyerCompanyById(Long companyId) {
        return buyerCompanyQueryService.handle(new GetBuyerCompanyByIdQuery(companyId));
    }

    public Optional<ProviderCompany> fetchProviderCompanyById(Long providerId) {
        return providerCompanyQueryService.handle(new GetProviderCompanyByIdQuery(providerId));
    }
}
