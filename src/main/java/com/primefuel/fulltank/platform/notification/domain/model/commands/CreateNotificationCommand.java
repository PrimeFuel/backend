package com.primefuel.fulltank.platform.notification.domain.model.commands;

import com.primefuel.fulltank.platform.notification.domain.model.valueobjects.NotificationType;

public record CreateNotificationCommand(Long userId, NotificationType type,
                                        String title, String message, Long referenceId) {
}