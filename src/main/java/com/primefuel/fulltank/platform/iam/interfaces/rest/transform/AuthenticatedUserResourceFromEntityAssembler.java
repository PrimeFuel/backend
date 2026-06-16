package com.primefuel.fulltank.platform.iam.interfaces.rest.transform;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.User;
import com.primefuel.fulltank.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;

public final class AuthenticatedUserResourceFromEntityAssembler {

    private AuthenticatedUserResourceFromEntityAssembler() {
    }

    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
