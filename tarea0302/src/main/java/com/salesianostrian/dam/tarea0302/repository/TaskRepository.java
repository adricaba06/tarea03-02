package com.salesianostrian.dam.tarea0302.repository;

import com.salesianostrian.dam.tarea0302.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserUsername(String username);
}
