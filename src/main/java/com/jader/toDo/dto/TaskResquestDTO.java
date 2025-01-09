package com.jader.toDo.dto;

import com.jader.toDo.entities.Task;
import com.jader.toDo.entities.User;
import com.jader.toDo.entities.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TaskResquestDTO {
    @NotNull(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @NotNull(message = "Creation date is required")
    private LocalDateTime creationDate;
    private LocalDateTime deadline;

    @NotNull(message = "User id is required")
    private Long userId;

    public TaskResquestDTO() {
    }

    public TaskResquestDTO(Task task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.creationDate = task.getCreationDate();
        this.status = task.getStatus();
        this.deadline = task.getDeadline();
    }

    public Task toTask(User user) {
        Task task = new Task();
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setStatus(this.status);
        task.setCreationDate(this.creationDate != null ? this.creationDate : LocalDateTime.now());
        task.setDeadline(this.deadline);
        task.setUser(user);
        return task;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
