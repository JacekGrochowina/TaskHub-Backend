package com.jg.taskhub.task.mapper;

import com.jg.taskhub.task.domain.TaskEntity;
import com.jg.taskhub.task.dto.CreateTaskRequest;
import com.jg.taskhub.task.dto.TaskResponse;
import com.jg.taskhub.task.dto.UpdateTaskRequest;
import com.jg.taskhub.user.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final UserMapper userMapper;

    public TaskMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public TaskResponse toResponse(TaskEntity entity) {
        return new TaskResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getPriority(),
                entity.getDeadline(),
                entity.getUser() != null ? userMapper.toResponse(entity.getUser()) : null,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public TaskEntity toEntity(CreateTaskRequest request) {
        TaskEntity task = new TaskEntity();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setDeadline(request.deadline());
        // User is set in Service
        return task;
    }

    public void updateEntity(TaskEntity task, UpdateTaskRequest request) {
        if (request.title() != null) {
            task.setTitle(request.title());
        }
        if (request.description() != null) {
            task.setDescription(request.description());
        }
        if (request.status() != null) {
            task.setStatus(request.status());
        }
        if (request.priority() != null) {
            task.setPriority(request.priority());
        }
        if (request.deadline() != null) {
            task.setDeadline(request.deadline());
        }
        // User update is handled in Service
    }
}
