package com.primefuel.fulltank.platform.fulfillment.application.commandservices;

import com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates.Delivery;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.CompleteDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.CreateDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.DispatchDeliveryCommand;
import com.primefuel.fulltank.platform.fulfillment.domain.model.commands.FailDeliveryCommand;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;

public interface DeliveryCommandService {
    Result<Delivery, ApplicationError> handle(CreateDeliveryCommand command);
    Result<Delivery, ApplicationError> handle(DispatchDeliveryCommand command);
    Result<Delivery, ApplicationError> handle(CompleteDeliveryCommand command);
    Result<Delivery, ApplicationError> handle(FailDeliveryCommand command);
}
