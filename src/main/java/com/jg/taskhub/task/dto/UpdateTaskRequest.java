package com.jg.taskhub.task.dto;

public record UpdateTaskRequest(
        String title,
        String description,
        String status
) {
}
