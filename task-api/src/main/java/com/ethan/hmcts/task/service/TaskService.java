package com.ethan.hmcts.task.service;

import com.ethan.hmcts.task.entity.TaskEntity;
import com.ethan.hmcts.task.model.TaskRequest;
import com.ethan.hmcts.task.model.TaskResponse;
import com.ethan.hmcts.task.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskResponse createTask(TaskRequest request) {
        TaskEntity entity = new TaskEntity(
            request.title(),
            request.description(),
            request.status(),
            request.dueDateTime()
        );
        

        TaskEntity saved = repository.save(entity);

        return new TaskResponse(
            saved.getId(),
            saved.getTitle(),
            saved.getDescription(),
            saved.getStatus(),
            saved.getDueDateTime(),
            saved.getCreatedAt()
        );
    }

    public List<TaskResponse> getAllTasks() {
        return repository.findAll().stream()
            .map(entity -> new TaskResponse(
                entity.getId(), 
                entity.getTitle(), 
                entity.getDescription(),
                entity.getStatus(), 
                entity.getDueDateTime(), 
                entity.getCreatedAt()
            ))
            .collect(Collectors.toList());
    }
}