package com.ethan.hmcts.task.entity;

import com.ethan.hmcts.task.model.TaskStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime dueDateTime;

    private LocalDateTime createdAt = LocalDateTime.now();

    protected TaskEntity() { }

    public TaskEntity(String title,
                    String description,
                    TaskStatus status,
                    LocalDateTime dueDateTime) {
                this.title = title;
                this.description = description;
                this.status = status;
                this.dueDateTime = dueDateTime;
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

    public TaskStatus getStatus(){
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }
    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
