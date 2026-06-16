package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.assemblers;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.User;
import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;

import java.util.HashSet;
import java.util.stream.Collectors;

public final class UserPersistenceAssembler {

    private UserPersistenceAssembler() {
    }

    public static User toDomainFromPersistence(UserPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new User();
        domain.setId(entity.getId());
        domain.setUsername(entity.getUsername());
        domain.setPassword(entity.getPassword());
        domain.setCompanyId(entity.getCompanyId());
        domain.setProviderId(entity.getProviderId());
        domain.setRoles(entity.getRoles().stream()
                .map(RolePersistenceAssembler::toDomainFromPersistence)
                .collect(Collectors.toSet()));
        return domain;
    }

    public static UserPersistenceEntity toPersistenceFromDomain(User user) {
        if (user == null) return null;
        var entity = new UserPersistenceEntity();
        if (user.getId() != null) {
            entity.setId(user.getId());
        }
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setCompanyId(user.getCompanyId());
        entity.setProviderId(user.getProviderId());
        entity.setRoles(user.getRoles() == null ? new HashSet<>()
                : user.getRoles().stream()
                        .map(RolePersistenceAssembler::toPersistenceFromDomain)
                        .collect(Collectors.toSet()));
        return entity;
    }
}
