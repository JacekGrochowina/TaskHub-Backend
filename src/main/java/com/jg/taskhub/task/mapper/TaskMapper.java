package com.jg.taskhub.task.mapper;

import com.jg.taskhub.task.domain.TaskEntity;
import com.jg.taskhub.task.dto.CreateTaskRequest;
import com.jg.taskhub.task.dto.TaskResponse;
import com.jg.taskhub.task.dto.UpdateTaskRequest;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse toResponse(TaskEntity entity) {
        return new TaskResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public TaskEntity toEntity(CreateTaskRequest request) {
        TaskEntity task = new TaskEntity();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        return task;
    }

    public void updateEntity(TaskEntity task, UpdateTaskRequest request) {
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
    }
}
