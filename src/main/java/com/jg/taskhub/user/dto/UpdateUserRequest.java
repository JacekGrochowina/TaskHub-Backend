package com.jg.taskhub.user.dto;

import com.jg.taskhub.user.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @Email
        @Size(max = 50)
        String email,

        String password,

        UserRole role
) {
}
