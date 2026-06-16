package com.primefuel.fulltank.platform.iam.interfaces.rest.transform;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.User;
import com.primefuel.fulltank.platform.iam.interfaces.rest.resources.UserResource;

public final class UserResourceFromEntityAssembler {

    private UserResourceFromEntityAssembler() {
    }

    public static UserResource toResourceFromEntity(User user) {
        java.util.List<String> roles = user.getRoles() != null
                ? user.getRoles().stream().map(r -> r.getName().name()).toList()
                : java.util.List.of();
        return new UserResource(user.getId(), user.getUsername(), roles,
                user.getCompanyId(), user.getProviderId());
    }
}
