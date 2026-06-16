package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.entities.BuyerCompanyPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerCompanyPersistenceRepository extends JpaRepository<BuyerCompanyPersistenceEntity, Long> {
}
