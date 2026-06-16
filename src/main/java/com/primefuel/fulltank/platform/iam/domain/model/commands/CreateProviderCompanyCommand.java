package com.primefuel.fulltank.platform.iam.domain.model.commands;

import java.util.List;

public record CreateProviderCompanyCommand(
        String name,
        String ruc,
        Double rating,
        String address,
        String phone,
        List<String> fuelTypesOffered,
        String description) {
}
