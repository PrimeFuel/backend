package com.primefuel.fulltank.platform.iam.interfaces.rest.resources;

import java.util.List;

public record CreateProviderCompanyResource(String name, String ruc, Double rating,
                                            String address, String phone,
                                            List<String> fuelTypesOffered, String description) {
}
