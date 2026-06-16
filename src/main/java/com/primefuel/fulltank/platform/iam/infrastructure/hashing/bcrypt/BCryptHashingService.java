package com.primefuel.fulltank.platform.iam.infrastructure.hashing.bcrypt;

import com.primefuel.fulltank.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
