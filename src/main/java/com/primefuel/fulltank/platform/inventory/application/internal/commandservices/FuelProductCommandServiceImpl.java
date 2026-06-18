package com.primefuel.fulltank.platform.inventory.application.internal.commandservices;

import com.primefuel.fulltank.platform.inventory.application.commandservices.FuelProductCommandService;
import com.primefuel.fulltank.platform.inventory.domain.model.aggregates.FuelProduct;
import com.primefuel.fulltank.platform.inventory.domain.model.commands.CreateFuelProductCommand;
import com.primefuel.fulltank.platform.inventory.domain.model.commands.DeleteFuelProductCommand;
import com.primefuel.fulltank.platform.inventory.domain.model.commands.UpdateFuelProductCommand;
import com.primefuel.fulltank.platform.inventory.domain.model.commands.UpdateFuelProductStockCommand;
import com.primefuel.fulltank.platform.inventory.domain.repositories.FuelProductRepository;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;

@Service
public class FuelProductCommandServiceImpl implements FuelProductCommandService {

    private final FuelProductRepository fuelProductRepository;

    public FuelProductCommandServiceImpl(FuelProductRepository fuelProductRepository) {
        this.fuelProductRepository = fuelProductRepository;
    }

    @Override
    public Result<FuelProduct, ApplicationError> handle(CreateFuelProductCommand command) {
        var fuelProduct = new FuelProduct(command);
        var saved = fuelProductRepository.save(fuelProduct);
        return Result.success(saved);
    }

    @Override
    public Result<FuelProduct, ApplicationError> handle(UpdateFuelProductStockCommand command) {
        var existing = fuelProductRepository.findById(command.fuelProductId());
        if (existing.isEmpty()) {
            return Result.failure(ApplicationError.notFound("FuelProduct", command.fuelProductId().toString()));
        }
        var fuelProduct = existing.get();
        fuelProduct.updateStock(command.newStock());
        var saved = fuelProductRepository.save(fuelProduct);
        return Result.success(saved);
    }

    @Override
    public Result<FuelProduct, ApplicationError> handle(UpdateFuelProductCommand command) {
        var existing = fuelProductRepository.findById(command.fuelProductId());
        if (existing.isEmpty()) {
            return Result.failure(ApplicationError.notFound("FuelProduct", command.fuelProductId().toString()));
        }
        var fuelProduct = existing.get();
        fuelProduct.update(command);
        var saved = fuelProductRepository.save(fuelProduct);
        return Result.success(saved);
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteFuelProductCommand command) {
        if (!fuelProductRepository.existsById(command.fuelProductId())) {
            return Result.failure(ApplicationError.notFound("FuelProduct", command.fuelProductId().toString()));
        }
        try {
            fuelProductRepository.deleteById(command.fuelProductId());
            return Result.success(command.fuelProductId());
        } catch (DataIntegrityViolationException | PersistenceException exception) {
            return Result.failure(ApplicationError.conflict(
                    "FuelProduct",
                    "Cannot delete a fuel product referenced by existing requests or orders"));
        }
    }

}
