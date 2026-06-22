package com.primefuel.fulltank.platform.iam.application.internal.queryservices;

import com.primefuel.fulltank.platform.iam.application.queryservices.BuyerCompanyQueryService;
import com.primefuel.fulltank.platform.iam.domain.model.aggregates.BuyerCompany;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetAllBuyerCompaniesQuery;
import com.primefuel.fulltank.platform.iam.domain.model.queries.GetBuyerCompanyByIdQuery;
import com.primefuel.fulltank.platform.iam.domain.repositories.BuyerCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerCompanyQueryServiceImpl implements BuyerCompanyQueryService {

    private final BuyerCompanyRepository buyerCompanyRepository;

    public BuyerCompanyQueryServiceImpl(BuyerCompanyRepository buyerCompanyRepository) {
        this.buyerCompanyRepository = buyerCompanyRepository;
    }

    @Override
    public Optional<BuyerCompany> handle(GetBuyerCompanyByIdQuery query) {
        return buyerCompanyRepository.findById(query.companyId());
    }

    @Override
    public List<BuyerCompany> handle(GetAllBuyerCompaniesQuery query) {
        return buyerCompanyRepository.findAll();
    }
}
