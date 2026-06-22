package com.primefuel.fulltank.platform.iam.domain.model.commands;

public record CreateBuyerCompanyCommand(
        String name,
        String ruc,
        String sector,
        String address,
        String contactEmail,
        String phone) {
}
