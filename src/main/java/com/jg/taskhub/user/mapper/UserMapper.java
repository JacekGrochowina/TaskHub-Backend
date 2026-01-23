package com.jg.taskhub.user.mapper;

import com.jg.taskhub.user.domain.UserEntity;
import com.jg.taskhub.user.dto.CreateUserRequest;
import com.jg.taskhub.user.dto.UpdateUserRequest;
import com.jg.taskhub.user.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(UserEntity entity) {
        return new UserResponse(
                entity.getId(),
                entity.getEmail(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public UserEntity toEntity(CreateUserRequest request) {
        UserEntity user = new UserEntity();
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole(request.role());
        return user;
    }

    public void updateEntity(UserEntity user, UpdateUserRequest request) {
        if (request.email() != null) {
            user.setEmail(request.email());
        }
        if (request.password() != null) {
            user.setPassword(request.password());
        }
        if (request.role() != null) {
            user.setRole(request.role());
        }
    }
}
