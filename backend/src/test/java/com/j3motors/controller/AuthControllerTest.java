package com.j3motors.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j3motors.dto.LoginRequest;
import com.j3motors.dto.RegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debe autenticar exitosamente al usuario admin y retornar token JWT")
    void testLoginExitoso() throws Exception {
        LoginRequest loginRequest = new LoginRequest("admin", "admin123");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", notNullValue()))
                .andExpect(jsonPath("$.username", is("admin")))
                .andExpect(jsonPath("$.tipo", is("Bearer")));
    }

    @Test
    @DisplayName("Debe retornar 401 Unauthorized con credenciales incorrectas")
    void testLoginCredencialesIncorrectas() throws Exception {
        LoginRequest loginRequest = new LoginRequest("admin", "password_incorrecta");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Debe registrar un nuevo usuario exitosamente")
    void testRegistrarUsuarioExitoso() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(
                "usuario_test_" + System.currentTimeMillis(),
                "password123",
                "Usuario de Prueba",
                "test_" + System.currentTimeMillis() + "@correo.com",
                Set.of("ROLE_CLIENTE")
        );

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje", containsString("exitosamente")));
    }
}
