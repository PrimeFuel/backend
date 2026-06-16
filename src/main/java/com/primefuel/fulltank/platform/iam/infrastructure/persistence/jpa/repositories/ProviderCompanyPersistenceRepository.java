package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.entities.ProviderCompanyPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderCompanyPersistenceRepository extends JpaRepository<ProviderCompanyPersistenceEntity, Long> {
}
