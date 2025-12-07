package com.ethan.hmcts.task.repository;

import com.ethan.hmcts.task.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    
}
