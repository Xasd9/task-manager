package com.name.task_manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Сохранение задачи в БД")
    void saveTask_shouldPersistTask() {
        Task task = new Task("Buy milk", "2 liters", "TODO");
        Task saved = taskRepository.save(task);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("Buy milk");
    }

    @Test
    @DisplayName("Поиск задачи по id")
    void findById_shouldReturnTask() {
        Task task = taskRepository.save(new Task("Test", "desc", "TODO"));
        var found = taskRepository.findById(task.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Test");
    }

    @Test
    @DisplayName("Получение всех задач")
    void findAll_shouldReturnAllTasks() {
        taskRepository.save(new Task("Task 1", "desc", "TODO"));
        taskRepository.save(new Task("Task 2", "desc", "DONE"));

        var tasks = taskRepository.findAll();
        assertThat(tasks).hasSize(2);
    }

    @Test
    @DisplayName("Удаление задачи")
    void deleteTask_shouldRemoveIt() {
        Task task = taskRepository.save(new Task("Delete me", "desc", "TODO"));
        taskRepository.delete(task);

        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
}