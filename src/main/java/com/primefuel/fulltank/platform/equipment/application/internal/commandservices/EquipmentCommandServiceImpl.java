package com.primefuel.fulltank.platform.equipment.application.internal.commandservices;

import com.primefuel.fulltank.platform.equipment.application.commandservices.EquipmentCommandService;
import com.primefuel.fulltank.platform.equipment.domain.model.aggregates.Equipment;
import com.primefuel.fulltank.platform.equipment.domain.model.commands.CreateEquipmentCommand;
import com.primefuel.fulltank.platform.equipment.domain.model.commands.UpdateEquipmentCommand;
import com.primefuel.fulltank.platform.equipment.domain.repositories.EquipmentRepository;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class EquipmentCommandServiceImpl implements EquipmentCommandService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentCommandServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Result<Equipment, ApplicationError> handle(CreateEquipmentCommand command) {
        var equipment = new Equipment(command);
        var saved = equipmentRepository.save(equipment);
        return Result.success(saved);
    }

    @Override
    public Result<Equipment, ApplicationError> handle(UpdateEquipmentCommand command) {
        var existing = equipmentRepository.findById(command.equipmentId());
        if (existing.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Equipment", command.equipmentId().toString()));
        }
        var equipment = existing.get();
        equipment.update(command);
        var saved = equipmentRepository.save(equipment);
        return Result.success(saved);
    }
}
