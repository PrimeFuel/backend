package com.primefuel.fulltank.platform.equipment.application.queryservices;

import com.primefuel.fulltank.platform.equipment.domain.model.aggregates.Equipment;
import com.primefuel.fulltank.platform.equipment.domain.model.queries.GetAllEquipmentQuery;
import com.primefuel.fulltank.platform.equipment.domain.model.queries.GetEquipmentByCompanyIdQuery;
import com.primefuel.fulltank.platform.equipment.domain.model.queries.GetEquipmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EquipmentQueryService {
    Optional<Equipment> handle(GetEquipmentByIdQuery query);
    List<Equipment> handle(GetAllEquipmentQuery query);
    List<Equipment> handle(GetEquipmentByCompanyIdQuery query);
}
