package com.jg.taskhub.task.dto;

import com.jg.taskhub.task.domain.TaskPriority;
import com.jg.taskhub.task.domain.TaskStatus;
import com.jg.taskhub.user.dto.UserResponse;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDateTime deadline,
        UserResponse user,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
