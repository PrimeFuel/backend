package com.primefuel.fulltank.platform.iam.interfaces.rest.resources;

public record CreateBuyerCompanyResource(String name, String ruc, String sector,
                                         String address, String contactEmail, String phone) {
}
