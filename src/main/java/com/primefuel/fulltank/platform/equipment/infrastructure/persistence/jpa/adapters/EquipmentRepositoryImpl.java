package com.primefuel.fulltank.platform.equipment.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.equipment.domain.model.aggregates.Equipment;
import com.primefuel.fulltank.platform.equipment.domain.repositories.EquipmentRepository;
import com.primefuel.fulltank.platform.equipment.infrastructure.persistence.jpa.assemblers.EquipmentPersistenceAssembler;
import com.primefuel.fulltank.platform.equipment.infrastructure.persistence.jpa.repositories.EquipmentPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepository {

    private final EquipmentPersistenceRepository equipmentPersistenceRepository;

    public EquipmentRepositoryImpl(EquipmentPersistenceRepository equipmentPersistenceRepository) {
        this.equipmentPersistenceRepository = equipmentPersistenceRepository;
    }

    @Override
    public Optional<Equipment> findById(Long id) {
        return equipmentPersistenceRepository.findById(id)
                .map(EquipmentPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentPersistenceRepository.findAll().stream()
                .map(EquipmentPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<Equipment> findByCompanyId(Long companyId) {
        return equipmentPersistenceRepository.findByCompanyId(companyId).stream()
                .map(EquipmentPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Equipment save(Equipment equipment) {
        var entity = EquipmentPersistenceAssembler.toPersistenceFromDomain(equipment);
        return EquipmentPersistenceAssembler.toDomainFromPersistence(
                equipmentPersistenceRepository.save(entity));
    }

    @Override
    public boolean existsById(Long id) {
        return equipmentPersistenceRepository.existsById(id);
    }
}
