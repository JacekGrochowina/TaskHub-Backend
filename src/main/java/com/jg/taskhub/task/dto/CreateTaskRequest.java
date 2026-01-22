package com.jg.taskhub.task.dto;

public record CreateTaskRequest(
        String title,
        String description,
        String status
) {
}
