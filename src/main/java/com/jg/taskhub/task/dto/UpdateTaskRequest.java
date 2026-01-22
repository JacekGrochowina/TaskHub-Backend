package com.jg.taskhub.task.dto;

import jakarta.validation.constraints.Size;

public record UpdateTaskRequest(
        @Size(max = 255)
        String title,

        String description,

        @Size(max = 30)
        String status
) {
}
