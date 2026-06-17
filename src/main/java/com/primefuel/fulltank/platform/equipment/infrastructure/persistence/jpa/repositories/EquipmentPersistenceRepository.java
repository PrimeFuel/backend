package com.primefuel.fulltank.platform.equipment.infrastructure.persistence.jpa.repositories;

import com.primefuel.fulltank.platform.equipment.infrastructure.persistence.jpa.entities.EquipmentPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentPersistenceRepository extends JpaRepository<EquipmentPersistenceEntity, Long> {
    List<EquipmentPersistenceEntity> findByCompanyId(Long companyId);
}
