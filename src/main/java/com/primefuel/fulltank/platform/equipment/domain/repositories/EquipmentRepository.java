package com.primefuel.fulltank.platform.equipment.domain.repositories;

import com.primefuel.fulltank.platform.equipment.domain.model.aggregates.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository {
    Optional<Equipment> findById(Long id);
    List<Equipment> findAll();
    List<Equipment> findByCompanyId(Long companyId);
    Equipment save(Equipment equipment);
    boolean existsById(Long id);
}
