package com.j3motors.controller;

import com.j3motors.model.Cita;
import com.j3motors.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<List<Cita>> listarCitas() {
        return ResponseEntity.ok(citaService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<Cita> registrarCita(@RequestBody Cita nuevaCita) {
        Cita guardada = citaService.registrarCita(nuevaCita);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }
}
