package com.ethan.hmcts.task.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TaskRequest(
    @NotBlank(message = "Title is required")
    String title,

    @NotBlank(message = "Description is required")
    String description,

    @NotNull(message = "Status is required")
    TaskStatus status,

    @NotNull(message = "Due date/time is required")
    LocalDateTime dueDateTime
) { }