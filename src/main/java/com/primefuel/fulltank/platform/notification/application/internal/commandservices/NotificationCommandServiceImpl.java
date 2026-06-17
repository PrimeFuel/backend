package com.primefuel.fulltank.platform.notification.application.internal.commandservices;

import com.primefuel.fulltank.platform.notification.application.commandservices.NotificationCommandService;
import com.primefuel.fulltank.platform.notification.domain.model.aggregates.Notification;
import com.primefuel.fulltank.platform.notification.domain.model.commands.CreateNotificationCommand;
import com.primefuel.fulltank.platform.notification.domain.model.commands.MarkNotificationAsReadCommand;
import com.primefuel.fulltank.platform.notification.domain.repositories.NotificationRepository;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Result<Notification, ApplicationError> handle(CreateNotificationCommand command) {
        var notification = new Notification(command);
        return Result.success(notificationRepository.save(notification));
    }

    @Override
    public Result<Notification, ApplicationError> handle(MarkNotificationAsReadCommand command) {
        var existing = notificationRepository.findById(command.notificationId());
        if (existing.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Notification", command.notificationId().toString()));
        }
        var notification = existing.get();
        notification.markAsRead();
        return Result.success(notificationRepository.save(notification));
    }
}