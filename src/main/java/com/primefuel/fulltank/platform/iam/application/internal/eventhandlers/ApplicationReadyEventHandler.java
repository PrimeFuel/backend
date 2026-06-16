package com.primefuel.fulltank.platform.iam.application.internal.eventhandlers;

import com.primefuel.fulltank.platform.iam.application.commandservices.RoleCommandService;
import com.primefuel.fulltank.platform.iam.domain.model.commands.SeedRolesCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Slf4j
public class ApplicationReadyEventHandler {

    private final RoleCommandService roleCommandService;

    public ApplicationReadyEventHandler(RoleCommandService roleCommandService) {
        this.roleCommandService = roleCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        log.info("Starting to verify if roles seeding is needed for {} at {}", applicationName, currentTimestamp());
        roleCommandService.handle(new SeedRolesCommand());
        log.info("Roles seeding verification finished for {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
