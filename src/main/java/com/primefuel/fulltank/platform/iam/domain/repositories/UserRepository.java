package com.primefuel.fulltank.platform.iam.domain.repositories;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByCompanyId(Long companyId);
    Optional<User> findByProviderId(Long providerId);
    List<User> findAll();
    User save(User user);
    boolean existsByUsername(String username);
}
