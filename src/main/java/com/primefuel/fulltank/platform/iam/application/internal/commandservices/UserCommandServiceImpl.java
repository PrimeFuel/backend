package com.primefuel.fulltank.platform.iam.application.internal.commandservices;

import com.primefuel.fulltank.platform.iam.application.commandservices.UserCommandService;
import com.primefuel.fulltank.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.primefuel.fulltank.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.primefuel.fulltank.platform.iam.domain.model.aggregates.User;
import com.primefuel.fulltank.platform.iam.domain.model.commands.SignInCommand;
import com.primefuel.fulltank.platform.iam.domain.model.commands.SignUpCommand;
import com.primefuel.fulltank.platform.iam.domain.repositories.RoleRepository;
import com.primefuel.fulltank.platform.iam.domain.repositories.UserRepository;
import com.primefuel.fulltank.platform.shared.application.result.ApplicationError;
import com.primefuel.fulltank.platform.shared.application.result.Result;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService,
            TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Result<ImmutablePair<User, String>, ApplicationError> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) {
            return Result.failure(ApplicationError.notFound("User", command.username()));
        }
        if (!hashingService.matches(command.password(), user.get().getPassword())) {
            return Result.failure(ApplicationError.validationError("credentials", "Invalid username or password"));
        }
        var token = tokenService.generateToken(user.get().getUsername());
        return Result.success(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Result<User, ApplicationError> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username())) {
            return Result.failure(ApplicationError.conflict("User", "Username already exists"));
        }
        var roles = command.roles().stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .toList();
        if (roles.stream().anyMatch(java.util.Optional::isEmpty)) {
            return Result.failure(ApplicationError.notFound("Role", "one or more role names"));
        }
        var resolvedRoles = roles.stream().map(java.util.Optional::get).toList();
        var user = new User(command.username(), hashingService.encode(command.password()),
                resolvedRoles, command.companyId(), command.providerId());
        userRepository.save(user);
        return userRepository.findByUsername(command.username())
                .<Result<User, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.unexpected("sign-up", "Created user could not be reloaded")));
    }
}
