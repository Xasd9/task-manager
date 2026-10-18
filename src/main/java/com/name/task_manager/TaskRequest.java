package com.name.task_manager;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class TaskRequest {
    @NotBlank (message = "Title cannot be empty")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")

    private String title;

    @Size (max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotBlank (message = "status cannot be empty")
    private String status;

    public TaskRequest() {

    }
        public TaskRequest(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription (String description) { this.description = description; }
    public String getStatus() { return status;}
    public void setStatus(String status) { this.status = status; }
}
