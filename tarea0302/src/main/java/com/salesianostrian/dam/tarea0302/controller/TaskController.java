package com.salesianostrian.dam.tarea0302.controller;

import com.salesianostrian.dam.tarea0302.dto.TaskRequest;
import com.salesianostrian.dam.tarea0302.dto.TaskResponse;
import com.salesianostrian.dam.tarea0302.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // 🔓 PÚBLICO
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    // 🔐 PROTEGIDO
    @PostMapping
    public TaskResponse createTask(
            @RequestBody TaskRequest request,
            Authentication authentication
    ) {
        return taskService.createTask(
                request,
                authentication.getName()
        );
    }
}