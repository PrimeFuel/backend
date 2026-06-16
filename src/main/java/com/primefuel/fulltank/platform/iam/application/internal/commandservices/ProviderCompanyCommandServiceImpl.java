package com.primefuel.fulltank.platform.iam.application.internal.commandservices;

import com.primefuel.fulltank.platform.iam.application.commandservices.ProviderCompanyCommandService;
import com.primefuel.fulltank.platform.iam.domain.model.aggregates.ProviderCompany;
import com.primefuel.fulltank.platform.iam.domain.model.commands.CreateProviderCompanyCommand;
import com.primefuel.fulltank.platform.iam.domain.repositories.ProviderCompanyRepository;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class ProviderCompanyCommandServiceImpl implements ProviderCompanyCommandService {

    private final ProviderCompanyRepository providerCompanyRepository;

    public ProviderCompanyCommandServiceImpl(ProviderCompanyRepository providerCompanyRepository) {
        this.providerCompanyRepository = providerCompanyRepository;
    }

    @Override
    public Result<ProviderCompany, ApplicationError> handle(CreateProviderCompanyCommand command) {
        try {
            var providerCompany = new ProviderCompany(command);
            var saved = providerCompanyRepository.save(providerCompany);
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("ProviderCompanyCommandService", e.getMessage()));
        }
    }
}
