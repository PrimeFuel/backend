package com.primefuel.fulltank.platform.iam.interfaces.rest.transform;

import com.primefuel.fulltank.platform.iam.domain.model.commands.SignInCommand;
import com.primefuel.fulltank.platform.iam.interfaces.rest.resources.SignInResource;

public final class SignInCommandFromResourceAssembler {

    private SignInCommandFromResourceAssembler() {
    }

    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}
