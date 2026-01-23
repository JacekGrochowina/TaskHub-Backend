package com.jg.taskhub.user.application;

import com.jg.taskhub.common.exception.UserNotFoundException;
import com.jg.taskhub.user.domain.UserEntity;
import com.jg.taskhub.user.dto.CreateUserRequest;
import com.jg.taskhub.user.dto.UpdateUserRequest;
import com.jg.taskhub.user.dto.UserResponse;
import com.jg.taskhub.user.infrastructure.UserRepository;
import com.jg.taskhub.user.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UserResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public UserResponse getById(Long id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return mapper.toResponse(user);
    }

    public UserResponse create(CreateUserRequest request) {
        UserEntity user = mapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        return mapper.toResponse(repository.save(user));
    }

    public UserResponse update(Long id, UpdateUserRequest request) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        mapper.updateEntity(user, request);
        
        if (request.password() != null) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        return mapper.toResponse(user);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
