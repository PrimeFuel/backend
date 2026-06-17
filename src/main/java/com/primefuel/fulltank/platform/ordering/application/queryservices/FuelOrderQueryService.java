package com.primefuel.fulltank.platform.ordering.application.queryservices;

import com.primefuel.fulltank.platform.ordering.domain.model.aggregates.FuelOrder;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetAllFuelOrdersQuery;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetFuelOrderByIdQuery;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetFuelOrdersByCompanyIdQuery;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetFuelOrdersByProviderIdQuery;

import java.util.List;
import java.util.Optional;

public interface FuelOrderQueryService {
    Optional<FuelOrder> handle(GetFuelOrderByIdQuery query);
    List<FuelOrder> handle(GetAllFuelOrdersQuery query);
    List<FuelOrder> handle(GetFuelOrdersByCompanyIdQuery query);
    List<FuelOrder> handle(GetFuelOrdersByProviderIdQuery query);
}
