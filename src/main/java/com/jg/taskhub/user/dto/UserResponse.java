package com.jg.taskhub.user.dto;

import com.jg.taskhub.user.domain.UserRole;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
