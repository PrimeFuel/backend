package com.primefuel.fulltank.platform.ordering.application.internal.commandservices;

import com.primefuel.fulltank.platform.inventory.domain.repositories.FuelProductRepository;
import com.primefuel.fulltank.platform.ordering.domain.model.aggregates.FuelOrder;
import com.primefuel.fulltank.platform.ordering.domain.model.commands.CreateFuelOrderCommand;
import com.primefuel.fulltank.platform.ordering.domain.model.valueobjects.RequestStatus;
import com.primefuel.fulltank.platform.ordering.domain.repositories.FuelOrderRepository;
import com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.entities.FuelRequestPersistenceEntity;
import com.primefuel.fulltank.platform.ordering.infrastructure.persistence.jpa.repositories.FuelRequestPersistenceRepository;
import com.primefuel.fulltank.platform.ordering.interfaces.rest.resources.CreateFuelRequestResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuelRequestService {
    private final FuelRequestPersistenceRepository requests;
    private final FuelProductRepository products;
    private final FuelOrderRepository orders;

    public FuelRequestService(FuelRequestPersistenceRepository requests,
                              FuelProductRepository products,
                              FuelOrderRepository orders) {
        this.requests = requests;
        this.products = products;
        this.orders = orders;
    }

    @Transactional
    public FuelRequestPersistenceEntity create(CreateFuelRequestResource resource) {
        var product = products.findById(resource.fuelProductId())
                .orElseThrow(() -> new IllegalArgumentException("Fuel product not found"));
        if (!product.getProviderId().equals(resource.providerId())) {
            throw new IllegalArgumentException("Fuel product does not belong to provider");
        }
        var request = new FuelRequestPersistenceEntity();
        request.setBuyerCompanyId(resource.buyerCompanyId());
        request.setProviderId(resource.providerId());
        request.setEquipmentId(resource.equipmentId());
        request.setFuelProductId(resource.fuelProductId());
        request.setFuelType(product.getFuelType().name());
        request.setProductName(product.getName());
        request.setQuantity(resource.quantity());
        request.setUnit(resource.unit() == null ? product.getUnit() : resource.unit());
        request.setUnitPrice(product.getPricePerUnit());
        request.setDeliveryAddress(resource.deliveryAddress());
        request.setDeliveryDate(resource.deliveryDate());
        request.setStatus(RequestStatus.PENDING);
        request.setSource(resource.source() == null ? "MANUAL" : resource.source().toUpperCase());
        return requests.save(request);
    }

    public List<FuelRequestPersistenceEntity> findAll(Long buyerCompanyId, Long providerId) {
        if (buyerCompanyId != null) return requests.findByBuyerCompanyId(buyerCompanyId);
        if (providerId != null) return requests.findByProviderId(providerId);
        return requests.findAll();
    }

    @Transactional
    public FuelOrder accept(Long requestId) {
        var request = requests.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Fuel request not found"));
        if (request.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Only pending requests can be accepted");
        }
        var product = products.findById(request.getFuelProductId())
                .orElseThrow(() -> new IllegalArgumentException("Fuel product not found"));
        var command = new CreateFuelOrderCommand(request.getBuyerCompanyId(), request.getProviderId(),
                request.getFuelProductId(), request.getEquipmentId(), request.getQuantity(),
                request.getDeliveryAddress(), request.getDeliveryDate());
        var order = new FuelOrder(command, product.getPricePerUnit() * request.getQuantity());
        order.setRequestId(requestId);
        order = orders.save(order);
        request.setStatus(RequestStatus.APPROVED);
        requests.save(request);
        return order;
    }

    @Transactional
    public FuelRequestPersistenceEntity reject(Long requestId, String reason) {
        var request = requests.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Fuel request not found"));
        if (request.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Only pending requests can be rejected");
        }
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("Rejection reason is required");
        }
        request.setStatus(RequestStatus.REJECTED);
        request.setRejectionReason(reason.trim());
        return requests.save(request);
    }
}
