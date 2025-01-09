package com.jader.toDo.dto;

import com.jader.toDo.entities.Task;
import com.jader.toDo.entities.User;
import com.jader.toDo.entities.enums.TaskStatus;

import java.time.LocalDateTime;

public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
    private UserResponseDTO user;

    public TaskResponseDTO() {
    }

    public TaskResponseDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.creationDate = task.getCreationDate();
        this.status = task.getStatus();
        this.deadline = task.getDeadline();
        this.user = new UserResponseDTO(task.getUser());
    }

    public Task toTask(User user) {
        Task task = new Task();
        task.setId(this.id);
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setStatus(this.status);
        task.setCreationDate(this.creationDate != null ? this.creationDate : LocalDateTime.now());
        task.setDeadline(this.deadline);
        task.setUser(user);
        return task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}
