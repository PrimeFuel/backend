package com.primefuel.fulltank.platform.iam.domain.repositories;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.BuyerCompany;

import java.util.List;
import java.util.Optional;

public interface BuyerCompanyRepository {
    Optional<BuyerCompany> findById(Long id);
    List<BuyerCompany> findAll();
    BuyerCompany save(BuyerCompany buyerCompany);
}
