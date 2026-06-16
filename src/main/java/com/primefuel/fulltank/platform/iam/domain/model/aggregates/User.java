package com.primefuel.fulltank.platform.iam.domain.model.aggregates;

import com.primefuel.fulltank.platform.iam.domain.model.entities.Role;
import com.primefuel.fulltank.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class User extends AbstractDomainAggregateRoot<User> {

    @Setter private Long id;
    @Setter private String username;
    @Setter private String password;
    @Setter private Set<Role> roles;
    @Setter private Long companyId;
    @Setter private Long providerId;

    public User() {
        this.roles = new HashSet<>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
    }

    public User(String username, String password, List<Role> roles, Long companyId, Long providerId) {
        this(username, password);
        addRoles(roles);
        this.companyId = companyId;
        this.providerId = providerId;
    }

    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public User addRoles(List<Role> roles) {
        var validated = Role.validateRoleSet(roles);
        this.roles.addAll(validated);
        return this;
    }
}
