package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.BuyerCompany;
import com.primefuel.fulltank.platform.iam.domain.repositories.BuyerCompanyRepository;
import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.assemblers.BuyerCompanyPersistenceAssembler;
import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.repositories.BuyerCompanyPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BuyerCompanyRepositoryImpl implements BuyerCompanyRepository {

    private final BuyerCompanyPersistenceRepository buyerCompanyRepository;

    public BuyerCompanyRepositoryImpl(BuyerCompanyPersistenceRepository buyerCompanyRepository) {
        this.buyerCompanyRepository = buyerCompanyRepository;
    }

    @Override
    public Optional<BuyerCompany> findById(Long id) {
        return buyerCompanyRepository.findById(id)
                .map(BuyerCompanyPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<BuyerCompany> findAll() {
        return buyerCompanyRepository.findAll().stream()
                .map(BuyerCompanyPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public BuyerCompany save(BuyerCompany buyerCompany) {
        var entity = BuyerCompanyPersistenceAssembler.toPersistenceFromDomain(buyerCompany);
        return BuyerCompanyPersistenceAssembler.toDomainFromPersistence(buyerCompanyRepository.save(entity));
    }
}
