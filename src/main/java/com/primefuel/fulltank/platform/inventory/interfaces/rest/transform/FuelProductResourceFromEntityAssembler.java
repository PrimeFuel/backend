package com.primefuel.fulltank.platform.inventory.interfaces.rest.transform;

import com.primefuel.fulltank.platform.inventory.domain.model.aggregates.FuelProduct;
import com.primefuel.fulltank.platform.inventory.interfaces.rest.resources.FuelProductResource;

public final class FuelProductResourceFromEntityAssembler {

    private FuelProductResourceFromEntityAssembler() {
    }

    public static FuelProductResource toResourceFromEntity(FuelProduct fuelProduct) {
        return new FuelProductResource(fuelProduct.getId(), fuelProduct.getName(),
                fuelProduct.getFuelType(), fuelProduct.getPricePerUnit(), fuelProduct.getUnit(),
                fuelProduct.getAvailableStock(), fuelProduct.getCapacity(), fuelProduct.getProviderId(),
                fuelProduct.getActive() == null || fuelProduct.getActive());
    }
}
