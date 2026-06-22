package com.primefuel.fulltank.platform.iam.interfaces.rest.transform;

import com.primefuel.fulltank.platform.iam.domain.model.commands.SignUpCommand;
import com.primefuel.fulltank.platform.iam.domain.model.entities.Role;
import com.primefuel.fulltank.platform.iam.interfaces.rest.resources.SignUpResource;

import java.util.List;

public final class SignUpCommandFromResourceAssembler {

    private SignUpCommandFromResourceAssembler() {
    }

    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null
                ? resource.roles().stream().map(Role::toRoleFromName).toList()
                : List.<Role>of();
        return new SignUpCommand(resource.username(), resource.password(), roles,
                resource.companyId(), resource.providerId());
    }
}
