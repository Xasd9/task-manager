package com.name.task_manager;

import java.util.List;

public interface TaskService {
    TaskResponse create(TaskRequest request);
    TaskResponse getById(Long id);
    List<TaskResponse> getAll();
    TaskResponse update(Long id, TaskRequest request);
    void delete(Long id);
}
