package com.jg.taskhub.task.dto;

import com.jg.taskhub.task.domain.TaskPriority;
import com.jg.taskhub.task.domain.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateTaskRequest(
        @NotBlank
        @Size(max = 255)
        String title,

        String description,

        @NotNull
        TaskStatus status,

        @NotNull
        TaskPriority priority,

        LocalDateTime deadline,

        Long userId
) {
}
