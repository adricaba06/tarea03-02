package com.salesianostrian.dam.tarea0302.service;

import com.salesianostrian.dam.tarea0302.dto.TaskRequest;
import com.salesianostrian.dam.tarea0302.dto.TaskResponse;
import com.salesianostrian.dam.tarea0302.model.Task;
import com.salesianostrian.dam.tarea0302.model.User;
import com.salesianostrian.dam.tarea0302.repository.TaskRepository;
import com.salesianostrian.dam.tarea0302.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // Endpoint protegido (requiere JWT)
    public TaskResponse createTask(TaskRequest request, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Task task = Task.builder()
                .description(request.description())
                .user(user)
                .build();

        taskRepository.save(task);

        return new TaskResponse(
                task.getId(),
                task.getDescription(),
                user.getUsername()
        );
    }

    // Endpoint público
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getDescription(),
                        task.getUser().getUsername()
                ))
                .toList();
    }

}
