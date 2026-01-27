package com.jg.taskhub.task.application;

import com.jg.taskhub.common.exception.TaskNotFoundException;
import com.jg.taskhub.common.exception.UserNotFoundException;
import com.jg.taskhub.task.domain.TaskEntity;
import com.jg.taskhub.task.dto.CreateTaskRequest;
import com.jg.taskhub.task.dto.TaskResponse;
import com.jg.taskhub.task.dto.UpdateTaskRequest;
import com.jg.taskhub.task.infrastructure.TaskRepository;
import com.jg.taskhub.task.mapper.TaskMapper;
import com.jg.taskhub.user.domain.UserEntity;
import com.jg.taskhub.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskMapper mapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public Page<TaskResponse> getAll(Pageable pageable) {
        return taskRepository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public TaskResponse getById(Long id) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return mapper.toResponse(task);
    }

    public TaskResponse create(CreateTaskRequest request) {
        TaskEntity task = mapper.toEntity(request);

        if (request.userId() != null) {
            UserEntity user = userRepository.findById(request.userId())
                    .orElseThrow(() -> new UserNotFoundException(request.userId()));
            task.setUser(user);
        }

        return mapper.toResponse(taskRepository.save(task));
    }

    public TaskResponse update(Long id, UpdateTaskRequest request) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        mapper.updateEntity(task, request);

        if (request.userId() != null) {
            UserEntity user = userRepository.findById(request.userId())
                    .orElseThrow(() -> new UserNotFoundException(request.userId()));
            task.setUser(user);
        } else if (request.userId() == null && request.title() == null && request.description() == null && request.status() == null && request.priority() == null && request.deadline() == null) {
            // If only userId is null in the request, it means we want to unassign the user
            task.setUser(null);
        }


        return mapper.toResponse(task);
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}
