package com.salesianostrian.dam.tarea0302.controller;

import com.salesianostrian.dam.tarea0302.dto.JwtUserResponse;
import com.salesianostrian.dam.tarea0302.dto.LoginRequest;
import com.salesianostrian.dam.tarea0302.model.User;
import com.salesianostrian.dam.tarea0302.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public JwtUserResponse login(@RequestBody LoginRequest request) {

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String fakeToken = "TOKEN_DE_PRUEBA";

        return new JwtUserResponse(user.getUsername(), fakeToken);
    }
}