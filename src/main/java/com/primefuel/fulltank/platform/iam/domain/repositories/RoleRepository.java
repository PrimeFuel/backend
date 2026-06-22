package com.primefuel.fulltank.platform.iam.domain.repositories;

import com.primefuel.fulltank.platform.iam.domain.model.entities.Role;
import com.primefuel.fulltank.platform.iam.domain.model.valueobjects.Roles;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(Roles name);
    List<Role> findAll();
    Role save(Role role);
    boolean existsByName(Roles name);
}
