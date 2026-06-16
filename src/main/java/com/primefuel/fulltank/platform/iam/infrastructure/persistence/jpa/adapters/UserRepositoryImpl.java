package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.adapters;

import com.primefuel.fulltank.platform.iam.domain.model.aggregates.User;
import com.primefuel.fulltank.platform.iam.domain.repositories.UserRepository;
import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.assemblers.UserPersistenceAssembler;
import com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.repositories.UserPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserPersistenceRepository userRepository;

    public UserRepositoryImpl(UserPersistenceRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
                .map(UserPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<User> findByCompanyId(Long companyId) {
        return userRepository.findByCompanyId(companyId)
                .map(UserPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<User> findByProviderId(Long providerId) {
        return userRepository.findByProviderId(providerId)
                .map(UserPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(UserPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public User save(User user) {
        var entity = UserPersistenceAssembler.toPersistenceFromDomain(user);
        return UserPersistenceAssembler.toDomainFromPersistence(userRepository.save(entity));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
