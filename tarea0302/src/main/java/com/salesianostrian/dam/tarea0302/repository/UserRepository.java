package com.salesianostrian.dam.tarea0302.repository;

import com.salesianostrian.dam.tarea0302.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
