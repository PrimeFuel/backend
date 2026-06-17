package com.primefuel.fulltank.platform.notification.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.notification.domain.model.aggregates.Notification;
import com.primefuel.fulltank.platform.notification.domain.repositories.NotificationRepository;
import com.primefuel.fulltank.platform.notification.infrastructure.persistence.jpa.assemblers.NotificationPersistenceAssembler;
import com.primefuel.fulltank.platform.notification.infrastructure.persistence.jpa.repositories.NotificationPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationPersistenceRepository notificationPersistenceRepository;

    public NotificationRepositoryImpl(NotificationPersistenceRepository notificationPersistenceRepository) {
        this.notificationPersistenceRepository = notificationPersistenceRepository;
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationPersistenceRepository.findById(id)
                .map(NotificationPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Notification> findByUserId(Long userId) {
        return notificationPersistenceRepository.findByUserId(userId).stream()
                .map(NotificationPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<Notification> findByUserIdAndReadFalse(Long userId) {
        return notificationPersistenceRepository.findByUserIdAndReadFalse(userId).stream()
                .map(NotificationPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Notification save(Notification notification) {
        var entity = NotificationPersistenceAssembler.toPersistenceFromDomain(notification);
        return NotificationPersistenceAssembler.toDomainFromPersistence(
                notificationPersistenceRepository.save(entity));
    }
}
