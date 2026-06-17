package com.primefuel.fulltank.platform.notification.application.commandservices;

import com.primefuel.fulltank.platform.notification.domain.model.aggregates.Notification;
import com.primefuel.fulltank.platform.notification.domain.model.commands.CreateNotificationCommand;
import com.primefuel.fulltank.platform.notification.domain.model.commands.MarkNotificationAsReadCommand;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;

public interface NotificationCommandService {
    Result<Notification, ApplicationError> handle(CreateNotificationCommand command);
    Result<Notification, ApplicationError> handle(MarkNotificationAsReadCommand command);
}
