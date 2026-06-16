package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.assemblers;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.ProviderCompany;
import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.entities.ProviderCompanyPersistenceEntity;

import java.util.ArrayList;

public final class ProviderCompanyPersistenceAssembler {

    private ProviderCompanyPersistenceAssembler() {
    }

    public static ProviderCompany toDomainFromPersistence(ProviderCompanyPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new ProviderCompany();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setRuc(entity.getRuc());
        domain.setRating(entity.getRating());
        domain.setAddress(entity.getAddress());
        domain.setPhone(entity.getPhone());
        domain.setFuelTypesOffered(entity.getFuelTypesOffered() != null ? new ArrayList<>(entity.getFuelTypesOffered()) : new ArrayList<>());
        domain.setDescription(entity.getDescription());
        return domain;
    }

    public static ProviderCompanyPersistenceEntity toPersistenceFromDomain(ProviderCompany domain) {
        if (domain == null) return null;
        var entity = new ProviderCompanyPersistenceEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setName(domain.getName());
        entity.setRuc(domain.getRuc());
        entity.setRating(domain.getRating());
        entity.setAddress(domain.getAddress());
        entity.setPhone(domain.getPhone());
        entity.setFuelTypesOffered(domain.getFuelTypesOffered() != null ? new ArrayList<>(domain.getFuelTypesOffered()) : new ArrayList<>());
        entity.setDescription(domain.getDescription());
        return entity;
    }
}
