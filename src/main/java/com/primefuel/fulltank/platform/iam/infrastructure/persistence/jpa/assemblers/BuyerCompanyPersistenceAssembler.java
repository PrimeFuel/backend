package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.assemblers;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.BuyerCompany;
import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.entities.BuyerCompanyPersistenceEntity;

public final class BuyerCompanyPersistenceAssembler {

    private BuyerCompanyPersistenceAssembler() {
    }

    public static BuyerCompany toDomainFromPersistence(BuyerCompanyPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new BuyerCompany();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setRuc(entity.getRuc());
        domain.setSector(entity.getSector());
        domain.setAddress(entity.getAddress());
        domain.setContactEmail(entity.getContactEmail());
        domain.setPhone(entity.getPhone());
        return domain;
    }

    public static BuyerCompanyPersistenceEntity toPersistenceFromDomain(BuyerCompany domain) {
        if (domain == null) return null;
        var entity = new BuyerCompanyPersistenceEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setName(domain.getName());
        entity.setRuc(domain.getRuc());
        entity.setSector(domain.getSector());
        entity.setAddress(domain.getAddress());
        entity.setContactEmail(domain.getContactEmail());
        entity.setPhone(domain.getPhone());
        return entity;
    }
}
