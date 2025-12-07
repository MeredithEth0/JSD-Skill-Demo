package com.ethan.hmcts.task.controller;

import com.ethan.hmcts.task.model.TaskRequest;
import com.ethan.hmcts.task.model.TaskResponse;
import com.ethan.hmcts.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@CrossOrigin(origins = {"http://localhost:5500", "http://localhost:8081", "http://localhost:3000"})
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        TaskResponse created = service.createTask(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
    return service.getAllTasks();
}


    
}