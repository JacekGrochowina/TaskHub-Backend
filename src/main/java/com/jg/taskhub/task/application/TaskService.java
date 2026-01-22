package com.jg.taskhub.task.application;

import com.jg.taskhub.task.domain.TaskEntity;
import com.jg.taskhub.task.dto.CreateTaskRequest;
import com.jg.taskhub.task.dto.TaskResponse;
import com.jg.taskhub.task.dto.UpdateTaskRequest;
import com.jg.taskhub.task.infrastructure.TaskRepository;
import com.jg.taskhub.task.mapper.TaskMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    public TaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TaskResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public TaskResponse getById(Long id) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapper.toResponse(task);
    }

    public TaskResponse create(CreateTaskRequest request) {
        TaskEntity task = mapper.toEntity(request);
        return mapper.toResponse(repository.save(task));
    }

    public TaskResponse update(Long id, UpdateTaskRequest request) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        mapper.updateEntity(task, request);
        return mapper.toResponse(task);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
