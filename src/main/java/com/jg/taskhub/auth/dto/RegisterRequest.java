package com.jg.taskhub.auth.dto;

import com.jg.taskhub.user.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Email @Size(max = 50) String email,
        @NotBlank String password,
        @NotNull UserRole role
) {
}
