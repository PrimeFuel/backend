package com.primefuel.fulltank.platform.fulfillment.application.internal.queryservices;

import com.primefuel.fulltank.platform.fulfillment.application.queryservices.DeliveryQueryService;
import com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates.Delivery;
import com.primefuel.fulltank.platform.fulfillment.domain.model.queries.GetAllDeliveriesQuery;
import com.primefuel.fulltank.platform.fulfillment.domain.model.queries.GetDeliveryByIdQuery;
import com.primefuel.fulltank.platform.fulfillment.domain.model.queries.GetDeliveryByOrderIdQuery;
import com.primefuel.fulltank.platform.fulfillment.domain.repositories.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryQueryServiceImpl implements DeliveryQueryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryQueryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Optional<Delivery> handle(GetDeliveryByIdQuery query) {
        return deliveryRepository.findById(query.deliveryId());
    }

    @Override
    public Optional<Delivery> handle(GetDeliveryByOrderIdQuery query) {
        return deliveryRepository.findByOrderId(query.orderId());
    }

    @Override
    public List<Delivery> handle(GetAllDeliveriesQuery query) {
        return deliveryRepository.findAll();
    }
}
