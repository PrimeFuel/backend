package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPersistenceRepository extends JpaRepository<UserPersistenceEntity, Long> {
    Optional<UserPersistenceEntity> findByUsername(String username);
    Optional<UserPersistenceEntity> findByCompanyId(Long companyId);
    Optional<UserPersistenceEntity> findByProviderId(Long providerId);
    boolean existsByUsername(String username);
}
