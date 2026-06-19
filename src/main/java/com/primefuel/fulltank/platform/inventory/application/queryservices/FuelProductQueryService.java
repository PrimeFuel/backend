package com.primefuel.fulltank.platform.inventory.application.queryservices;

import com.primefuel.fulltank.platform.inventory.domain.model.aggregates.FuelProduct;
import com.primefuel.fulltank.platform.inventory.domain.model.queries.GetAllFuelProductsQuery;
import com.primefuel.fulltank.platform.inventory.domain.model.queries.GetFuelProductByIdQuery;
import com.primefuel.fulltank.platform.inventory.domain.model.queries.GetFuelProductsByProviderIdQuery;

import java.util.List;
import java.util.Optional;

public interface FuelProductQueryService {
    Optional<FuelProduct> handle(GetFuelProductByIdQuery query);
    List<FuelProduct> handle(GetAllFuelProductsQuery query);
    List<FuelProduct> handle(GetFuelProductsByProviderIdQuery query);
}

