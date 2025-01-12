package com.jader.toDo.dto;

import com.jader.toDo.entities.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TaskUpdateRequestDTO {
    private String title;

    private String description;

    private TaskStatus status;

    private LocalDateTime creationDate;

    private LocalDateTime deadline;

    public TaskUpdateRequestDTO() {
    }

    public TaskUpdateRequestDTO(String title, String description, TaskStatus status, LocalDateTime creationDate, LocalDateTime deadline) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.deadline = deadline;
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
}
