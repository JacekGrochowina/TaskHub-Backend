package com.jg.taskhub.common.config;

import com.jg.taskhub.task.domain.TaskEntity;
import com.jg.taskhub.task.domain.TaskPriority;
import com.jg.taskhub.task.domain.TaskStatus;
import com.jg.taskhub.task.infrastructure.TaskRepository;
import com.jg.taskhub.user.domain.UserEntity;
import com.jg.taskhub.user.domain.UserRole;
import com.jg.taskhub.user.infrastructure.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, TaskRepository taskRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        UserEntity user = createuserIfNotFound("user@example.com", "password", UserRole.USER);
        UserEntity admin = createuserIfNotFound("admin@example.com", "admin", UserRole.ADMIN);

        if (taskRepository.count() == 0) {
            createTask("Zrobić zakupy", "Kup mleko, chleb i jajka", TaskStatus.TODO, TaskPriority.MEDIUM, null, user);
            createTask("Nauczyć się Spring Boot", "Przerobić tutorial", TaskStatus.IN_PROGRESS, TaskPriority.HIGH, LocalDateTime.now().plusDays(7), user);
            createTask("Zarządzać projektem", "Spotkanie z zespołem", TaskStatus.TODO, TaskPriority.HIGH, LocalDateTime.now().plusDays(1), admin);
        }
    }

    private UserEntity createuserIfNotFound(String email, String password, UserRole role) {
        return userRepository.findByEmail(email).orElseGet(() -> {
            UserEntity newUser = new UserEntity();
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRole(role);
            return userRepository.save(newUser);
        });
    }

    private void createTask(String title, String description, TaskStatus status, TaskPriority priority, LocalDateTime deadline, UserEntity user) {
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setPriority(priority);
        task.setDeadline(deadline);
        task.setUser(user);
        taskRepository.save(task);
    }
}
