package com.primefuel.fulltank.platform.iam.application.internal.commandservices;

import com.primefuel.fulltank.platform.iam.application.commandservices.BuyerCompanyCommandService;
import com.primefuel.fulltank.platform.iam.domain.model.aggregates.BuyerCompany;
import com.primefuel.fulltank.platform.iam.domain.model.commands.CreateBuyerCompanyCommand;
import com.primefuel.fulltank.platform.iam.domain.repositories.BuyerCompanyRepository;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class BuyerCompanyCommandServiceImpl implements BuyerCompanyCommandService {

    private final BuyerCompanyRepository buyerCompanyRepository;

    public BuyerCompanyCommandServiceImpl(BuyerCompanyRepository buyerCompanyRepository) {
        this.buyerCompanyRepository = buyerCompanyRepository;
    }

    @Override
    public Result<BuyerCompany, ApplicationError> handle(CreateBuyerCompanyCommand command) {
        try {
            var buyerCompany = new BuyerCompany(command);
            var saved = buyerCompanyRepository.save(buyerCompany);
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("BuyerCompanyCommandService", e.getMessage()));
        }
    }
}
