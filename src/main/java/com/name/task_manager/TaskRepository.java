package com.name.task_manager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Найти все задачи конкретного пользователя
    List<Task> findAllByUser(User user);
}