package com.primefuel.fulltank.platform.equipment.application.internal.queryservices;

import com.primefuel.fulltank.platform.equipment.application.queryservices.EquipmentQueryService;
import com.primefuel.fulltank.platform.equipment.domain.model.aggregates.Equipment;
import com.primefuel.fulltank.platform.equipment.domain.model.queries.GetAllEquipmentQuery;
import com.primefuel.fulltank.platform.equipment.domain.model.queries.GetEquipmentByCompanyIdQuery;
import com.primefuel.fulltank.platform.equipment.domain.model.queries.GetEquipmentByIdQuery;
import com.primefuel.fulltank.platform.equipment.domain.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentQueryServiceImpl implements EquipmentQueryService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentQueryServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Optional<Equipment> handle(GetEquipmentByIdQuery query) {
        return equipmentRepository.findById(query.equipmentId());
    }

    @Override
    public List<Equipment> handle(GetAllEquipmentQuery query) {
        return equipmentRepository.findAll();
    }

    @Override
    public List<Equipment> handle(GetEquipmentByCompanyIdQuery query) {
        return equipmentRepository.findByCompanyId(query.companyId());
    }
}
