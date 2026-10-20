package com.name.task_manager;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TaskResponse create(TaskRequest request) {
        User currentUser = getCurrentUser();
        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                currentUser
        );
        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    @Override
    public TaskResponse getById(Long id) {
        User currentUser = getCurrentUser();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Access denied");
        }
        return mapToResponse(task);
    }

    @Override
    public List<TaskResponse> getAll() {
        User currentUser = getCurrentUser();
        return taskRepository.findAllByUser(currentUser)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TaskResponse update(Long id, TaskRequest request) {
        User currentUser = getCurrentUser();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Access denied");
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());

        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {
        User currentUser = getCurrentUser();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Access denied");
        }
        taskRepository.deleteById(id);
    }

    // Вспомогательный метод для получения текущего пользователя из токена
    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }
}