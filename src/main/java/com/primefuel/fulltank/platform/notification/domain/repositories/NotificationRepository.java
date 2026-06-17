package com.primefuel.fulltank.platform.notification.domain.repositories;

import com.primefuel.fulltank.platform.notification.domain.model.aggregates.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    Optional<Notification> findById(Long id);
    List<Notification> findByUserId(Long userId);
    List<Notification> findByUserIdAndReadFalse(Long userId);
    Notification save(Notification notification);
}
