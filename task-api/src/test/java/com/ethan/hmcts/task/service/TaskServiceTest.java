package com.ethan.hmcts.task.service;

import com.ethan.hmcts.task.entity.TaskEntity;
import com.ethan.hmcts.task.model.TaskRequest;
import com.ethan.hmcts.task.model.TaskResponse;
import com.ethan.hmcts.task.model.TaskStatus;
import com.ethan.hmcts.task.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    @Test
    void createTask_savesAndReturnsTask() {
        // Arrange: mock the repository and create a real service
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);

        LocalDateTime due = LocalDateTime.now().plusDays(1);
        TaskRequest request = new TaskRequest(
                "Test task",
                "Test description",
                TaskStatus.PENDING,
                due
        );

        // Stub repository.save(...) to return a TaskEntity with an id
        TaskEntity savedEntity = new TaskEntity(
                request.title(),
                request.description(),
                request.status(),
                request.dueDateTime()
        );
        savedEntity.setId(1L); // make sure TaskEntity has setId(Long id)

        when(taskRepository.save(any(TaskEntity.class))).thenReturn(savedEntity);

        // Act: call the service
        TaskResponse response = taskService.createTask(request);

        // Assert: repository.save was called with the right data
        ArgumentCaptor<TaskEntity> captor = ArgumentCaptor.forClass(TaskEntity.class);
        verify(taskRepository, times(1)).save(captor.capture());
        TaskEntity passedToRepo = captor.getValue();

        assertThat(passedToRepo.getTitle()).isEqualTo("Test task");
        assertThat(passedToRepo.getStatus()).isEqualTo(TaskStatus.PENDING);
        assertThat(passedToRepo.getDueDateTime()).isEqualTo(due);

        // And the response matches the saved entity
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.title()).isEqualTo("Test task");
        assertThat(response.description()).isEqualTo("Test description");
        assertThat(response.status()).isEqualTo(TaskStatus.PENDING);
        assertThat(response.dueDateTime()).isEqualTo(due);
    }
}
