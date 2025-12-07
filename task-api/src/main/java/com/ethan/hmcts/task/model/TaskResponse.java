package com.ethan.hmcts.task.model;

import java.time.LocalDateTime;

public record TaskResponse(
    Long id,
    String title,
    String description,
    TaskStatus status,
    LocalDateTime dueDateTime,
    LocalDateTime createdAt
) { }