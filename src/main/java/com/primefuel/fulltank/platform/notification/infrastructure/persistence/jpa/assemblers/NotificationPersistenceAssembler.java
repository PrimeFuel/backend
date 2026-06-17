package com.primefuel.fulltank.platform.notification.infrastructure.persistence.jpa.assemblers;

import com.primefuel.fulltank.platform.notification.domain.model.aggregates.Notification;
import com.primefuel.fulltank.platform.notification.infrastructure.persistence.jpa.entities.NotificationPersistenceEntity;

public final class NotificationPersistenceAssembler {

    private NotificationPersistenceAssembler() {
    }

    public static Notification toDomainFromPersistence(NotificationPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new Notification();
        domain.setId(entity.getId());
        domain.setUserId(entity.getUserId());
        domain.setType(entity.getType());
        domain.setTitle(entity.getTitle());
        domain.setMessage(entity.getMessage());
        domain.setRead(entity.isRead());
        domain.setReferenceId(entity.getReferenceId());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }

    public static NotificationPersistenceEntity toPersistenceFromDomain(Notification domain) {
        if (domain == null) return null;
        var entity = new NotificationPersistenceEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setUserId(domain.getUserId());
        entity.setType(domain.getType());
        entity.setTitle(domain.getTitle());
        entity.setMessage(domain.getMessage());
        entity.setRead(domain.isRead());
        entity.setReferenceId(domain.getReferenceId());
        return entity;
    }
}
