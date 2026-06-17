package com.primefuel.fulltank.platform.notification.interfaces.rest;

import com.primefuel.fulltank.platform.notification.application.commandservices.NotificationCommandService;
import com.primefuel.fulltank.platform.notification.application.queryservices.NotificationQueryService;
import com.primefuel.fulltank.platform.notification.domain.model.commands.MarkNotificationAsReadCommand;
import com.primefuel.fulltank.platform.notification.domain.model.queries.GetNotificationByIdQuery;
import com.primefuel.fulltank.platform.notification.domain.model.queries.GetNotificationsByUserIdQuery;
import com.primefuel.fulltank.platform.notification.domain.model.queries.GetUnreadNotificationsByUserIdQuery;
import com.primefuel.fulltank.platform.notification.interfaces.rest.resources.CreateNotificationResource;
import com.primefuel.fulltank.platform.notification.interfaces.rest.resources.NotificationResource;
import com.primefuel.fulltank.platform.notification.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.primefuel.fulltank.platform.notification.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import com.primefuel.fulltank.platform.iam.domain.repositories.UserRepository;
import com.primefuel.fulltank.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notifications", description = "Notification management endpoints")
public class NotificationsController {

    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;
    private final UserRepository userRepository;

    public NotificationsController(NotificationCommandService notificationCommandService,
                                   NotificationQueryService notificationQueryService,
                                   UserRepository userRepository) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> createNotification(@RequestBody CreateNotificationResource resource) {
        var userId = resolveUserId(resource);
        if (userId == null) {
            return ResponseEntity.badRequest().body("Notification recipient does not exist");
        }
        var resolved = new CreateNotificationResource(userId, resource.companyId(), resource.providerId(),
                resource.type(), resource.title(), resource.message(), resource.referenceId());
        var command = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(resolved);
        var result = notificationCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                NotificationResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }

    @PostMapping("/{notificationId}/mark-as-read")
    public ResponseEntity<?> markAsRead(@PathVariable Long notificationId) {
        var result = notificationCommandService.handle(new MarkNotificationAsReadCommand(notificationId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                NotificationResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long notificationId) {
        var result = notificationQueryService.handle(new GetNotificationByIdQuery(notificationId));
        return result.map(n -> new ResponseEntity<>(
                        NotificationResourceFromEntityAssembler.toResourceFromEntity(n), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResource>> getNotificationsByUser(@PathVariable Long userId) {
        var notifications = notificationQueryService.handle(new GetNotificationsByUserIdQuery(userId));
        var resources = notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/buyer/{companyId}")
    public ResponseEntity<List<NotificationResource>> getNotificationsByBuyer(@PathVariable Long companyId) {
        return userRepository.findByCompanyId(companyId)
                .map(user -> getNotificationsByUser(user.getId()))
                .orElse(ResponseEntity.ok(List.of()));
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<NotificationResource>> getNotificationsByProvider(@PathVariable Long providerId) {
        return userRepository.findByProviderId(providerId)
                .map(user -> getNotificationsByUser(user.getId()))
                .orElse(ResponseEntity.ok(List.of()));
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<NotificationResource>> getUnreadByUser(@PathVariable Long userId) {
        var notifications = notificationQueryService.handle(new GetUnreadNotificationsByUserIdQuery(userId));
        var resources = notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    private Long resolveUserId(CreateNotificationResource resource) {
        if (resource.userId() != null) return resource.userId();
        if (resource.companyId() != null) {
            return userRepository.findByCompanyId(resource.companyId()).map(user -> user.getId()).orElse(null);
        }
        if (resource.providerId() != null) {
            return userRepository.findByProviderId(resource.providerId()).map(user -> user.getId()).orElse(null);
        }
        return null;
    }
}
