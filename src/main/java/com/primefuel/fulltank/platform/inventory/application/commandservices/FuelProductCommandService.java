package com.primefuel.fulltank.platform.inventory.application.commandservices;

import com.primefuel.fulltank.platform.inventory.domain.model.aggregates.FuelProduct;
import com.primefuel.fulltank.platform.inventory.domain.model.commands.CreateFuelProductCommand;
import com.primefuel.fulltank.platform.inventory.domain.model.commands.DeleteFuelProductCommand;
import com.primefuel.fulltank.platform.inventory.domain.model.commands.UpdateFuelProductCommand;
import com.primefuel.fulltank.platform.inventory.domain.model.commands.UpdateFuelProductStockCommand;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;

public interface FuelProductCommandService {
    Result<FuelProduct, ApplicationError> handle(CreateFuelProductCommand command);
    Result<FuelProduct, ApplicationError> handle(UpdateFuelProductStockCommand command);
    Result<FuelProduct, ApplicationError> handle(UpdateFuelProductCommand command);
    Result<Long, ApplicationError> handle(DeleteFuelProductCommand command);
}
