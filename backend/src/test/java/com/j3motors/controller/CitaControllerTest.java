package com.j3motors.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j3motors.model.Cita;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debe permitir el registro público de una nueva cita (POST /api/citas)")
    void testRegistrarCitaPublica() throws Exception {
        Cita nuevaCita = new Cita(
                null,
                "Mariana Lopez",
                "+51 912345678",
                "mariana@test.com",
                "Honda CB190R",
                "9876-BC",
                "Sistema de Frenos y Suspensión",
                "2026-10-25",
                "15:00",
                "Revisión de pastillas de freno traseras"
        );

        mockMvc.perform(post("/api/citas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevaCita)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nombreCliente", is("Mariana Lopez")))
                .andExpect(jsonPath("$.estado", is("PENDIENTE")));
    }

    @Test
    @DisplayName("Debe rechazar la consulta de citas sin autenticación (401 Unauthorized)")
    void testListarCitasSinAutenticacion_DebeRetornar401() throws Exception {
        mockMvc.perform(get("/api/citas"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @DisplayName("Debe listar las citas con usuario autenticado (200 OK)")
    void testListarCitasConAutenticacion_DebeRetornar200() throws Exception {
        mockMvc.perform(get("/api/citas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }
}
