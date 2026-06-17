package com.primefuel.fulltank.platform.ordering.application.internal.queryservices;

import com.primefuel.fulltank.platform.ordering.application.queryservices.FuelOrderQueryService;
import com.primefuel.fulltank.platform.ordering.domain.model.aggregates.FuelOrder;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetAllFuelOrdersQuery;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetFuelOrderByIdQuery;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetFuelOrdersByCompanyIdQuery;
import com.primefuel.fulltank.platform.ordering.domain.model.queries.GetFuelOrdersByProviderIdQuery;
import com.primefuel.fulltank.platform.ordering.domain.repositories.FuelOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelOrderQueryServiceImpl implements FuelOrderQueryService {

    private final FuelOrderRepository fuelOrderRepository;

    public FuelOrderQueryServiceImpl(FuelOrderRepository fuelOrderRepository) {
        this.fuelOrderRepository = fuelOrderRepository;
    }

    @Override
    public Optional<FuelOrder> handle(GetFuelOrderByIdQuery query) {
        return fuelOrderRepository.findById(query.orderId());
    }

    @Override
    public List<FuelOrder> handle(GetAllFuelOrdersQuery query) {
        return fuelOrderRepository.findAll();
    }

    @Override
    public List<FuelOrder> handle(GetFuelOrdersByCompanyIdQuery query) {
        return fuelOrderRepository.findByCompanyId(query.companyId());
    }

    @Override
    public List<FuelOrder> handle(GetFuelOrdersByProviderIdQuery query) {
        return fuelOrderRepository.findByProviderId(query.providerId());
    }
}
