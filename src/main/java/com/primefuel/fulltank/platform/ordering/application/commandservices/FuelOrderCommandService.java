package com.primefuel.fulltank.platform.ordering.application.commandservices;

import com.primefuel.fulltank.platform.ordering.domain.model.aggregates.FuelOrder;
import com.primefuel.fulltank.platform.ordering.domain.model.commands.CancelFuelOrderCommand;
import com.primefuel.fulltank.platform.ordering.domain.model.commands.ConfirmFuelOrderCommand;
import com.primefuel.fulltank.platform.ordering.domain.model.commands.CreateFuelOrderCommand;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;

public interface FuelOrderCommandService {
    Result<FuelOrder, ApplicationError> handle(CreateFuelOrderCommand command);
    Result<FuelOrder, ApplicationError> handle(ConfirmFuelOrderCommand command);
    Result<FuelOrder, ApplicationError> handle(CancelFuelOrderCommand command);
}
