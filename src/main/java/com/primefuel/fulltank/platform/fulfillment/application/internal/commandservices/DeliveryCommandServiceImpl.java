package com.primefuel.fulltank.platform.fulfillment.application.internal.commandservices;

import com.primefuel.fulltank.platform.equipment.domain.repositories.EquipmentRepository;
import com.primefuel.fulltank.platform.fulfillment.application.commandservices.DeliveryCommandService;
import com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates.Delivery;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.CompleteDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.CreateDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.DispatchDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.FailDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.domain.repositories.DeliveryRepository;
import com.primefuel.fulltank.platform.fulfillment.domain.repositories.DriverRepository;
import com.primefuel.fulltank.platform.fulfillment.domain.repositories.VehicleRepository;
import com.primefuel.fulltank.platform.inventory.domain.repositories.FuelProductRepository;
import com.primefuel.fulltank.platform.ordering.domain.repositories.FuelOrderRepository;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryCommandServiceImpl implements DeliveryCommandService {

    private final DeliveryRepository deliveryRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final FuelOrderRepository orderRepository;
    private final FuelProductRepository productRepository;
    private final EquipmentRepository equipmentRepository;

    public DeliveryCommandServiceImpl(DeliveryRepository deliveryRepository,
                                      DriverRepository driverRepository,
                                      VehicleRepository vehicleRepository,
                                      FuelOrderRepository orderRepository,
                                      FuelProductRepository productRepository,
                                      EquipmentRepository equipmentRepository) {
        this.deliveryRepository = deliveryRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    @Transactional
    public Result<Delivery, ApplicationError> handle(CreateDeliveryCommand command) {
        var driver = driverRepository.findById(command.driverId());
        var vehicle = vehicleRepository.findById(command.vehicleId());
        if (driver.isEmpty() || vehicle.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Fulfillment resource", "driver or vehicle"));
        }
        if (!command.providerId().equals(driver.get().getProviderId())
                || !command.providerId().equals(vehicle.get().getProviderId())) {
            return Result.failure(ApplicationError.conflict("Delivery",
                    "Driver and vehicle must belong to the selected provider"));
        }
        if (!isAvailable(driver.get().getStatus()) || !isAvailable(vehicle.get().getStatus())) {
            return Result.failure(ApplicationError.conflict("Delivery", "Driver or vehicle is not available"));
        }
        var order = orderRepository.findById(command.orderId());
        if (order.isEmpty() || !command.providerId().equals(order.get().getProviderId())) {
            return Result.failure(ApplicationError.notFound("FuelOrder", command.orderId().toString()));
        }
        if (vehicle.get().getCapacity() < order.get().getRequestedQuantity()) {
            return Result.failure(ApplicationError.conflict("Delivery", "Vehicle capacity is insufficient"));
        }
        var product = productRepository.findById(order.get().getFuelProductId());
        if (product.isEmpty() || product.get().getAvailableStock() < order.get().getRequestedQuantity()) {
            return Result.failure(ApplicationError.conflict("Delivery", "Insufficient inventory stock"));
        }
        if (deliveryRepository.findByOrderId(command.orderId()).isPresent()) {
            return Result.failure(ApplicationError.conflict("Delivery",
                    "A delivery already exists for order " + command.orderId()));
        }
        driver.get().setStatus("ASSIGNED");
        vehicle.get().setStatus("IN_ROUTE");
        product.get().updateStock(product.get().getAvailableStock() - order.get().getRequestedQuantity());
        order.get().dispatch();
        driverRepository.save(driver.get());
        vehicleRepository.save(vehicle.get());
        productRepository.save(product.get());
        orderRepository.save(order.get());
        var delivery = new Delivery(command);
        delivery.dispatch();
        return Result.success(deliveryRepository.save(delivery));
    }

    @Override
    public Result<Delivery, ApplicationError> handle(DispatchDeliveryCommand command) {
        var existing = deliveryRepository.findById(command.deliveryId());
        if (existing.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Delivery", command.deliveryId().toString()));
        }
        var delivery = existing.get();
        delivery.dispatch();
        return Result.success(deliveryRepository.save(delivery));
    }

    @Override
    @Transactional
    public Result<Delivery, ApplicationError> handle(CompleteDeliveryCommand command) {
        var existing = deliveryRepository.findById(command.deliveryId());
        if (existing.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Delivery", command.deliveryId().toString()));
        }
        var delivery = existing.get();
        var order = orderRepository.findById(delivery.getOrderId());
        if (order.isEmpty()) {
            return Result.failure(ApplicationError.notFound("FuelOrder", delivery.getOrderId().toString()));
        }
        driverRepository.findById(delivery.getDriverId()).ifPresent(driver -> {
            driver.setStatus("AVAILABLE");
            driverRepository.save(driver);
        });
        vehicleRepository.findById(delivery.getVehicleId()).ifPresent(vehicle -> {
            vehicle.setStatus("AVAILABLE");
            vehicleRepository.save(vehicle);
        });
        if (order.get().getEquipmentId() != null) {
            equipmentRepository.findById(order.get().getEquipmentId()).ifPresent(equipment -> {
                equipment.receiveFuel(order.get().getRequestedQuantity());
                equipmentRepository.save(equipment);
            });
        }
        order.get().receive();
        orderRepository.save(order.get());
        delivery.complete();
        return Result.success(deliveryRepository.save(delivery));
    }

    @Override
    public Result<Delivery, ApplicationError> handle(FailDeliveryCommand command) {
        var existing = deliveryRepository.findById(command.deliveryId());
        if (existing.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Delivery", command.deliveryId().toString()));
        }
        var delivery = existing.get();
        delivery.fail(command.reason());
        return Result.success(deliveryRepository.save(delivery));
    }

    private static boolean isAvailable(String status) {
        return "AVAILABLE".equalsIgnoreCase(status) || "ACTIVE".equalsIgnoreCase(status);
    }
}
