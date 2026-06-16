package com.primefuel.fulltank.platform.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String username, List<String> roles, Long companyId, Long providerId) {
}
