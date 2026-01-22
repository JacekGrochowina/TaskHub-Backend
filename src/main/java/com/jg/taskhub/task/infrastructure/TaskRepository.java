package com.jg.taskhub.task.infrastructure;

import com.jg.taskhub.task.domain.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
