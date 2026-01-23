package com.jg.taskhub.user.dto;

import com.jg.taskhub.user.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank
        @Email
        @Size(max = 50)
        String email,

        @NotBlank
        String password,

        @NotNull
        UserRole role
) {
}
