package com.primefuel.fulltank.platform.notification.interfaces.rest.resources;

import com.primefuel.fulltank.platform.notification.domain.model.valueobjects.NotificationType;

public record CreateNotificationResource(Long userId, Long companyId, Long providerId,
                                         NotificationType type, String title, String message,
                                         Long referenceId) {
}