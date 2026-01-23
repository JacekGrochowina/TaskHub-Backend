package com.jg.taskhub.task.dto;

import com.jg.taskhub.task.domain.TaskStatus;
import jakarta.validation.constraints.Size;

public record UpdateTaskRequest(
        @Size(max = 255)
        String title,

        String description,

        TaskStatus status
) {
}
