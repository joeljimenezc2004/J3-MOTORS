package com.j3motors.controller;

import com.j3motors.model.Repuesto;
import com.j3motors.service.RepuestoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
@CrossOrigin(origins = "*")
public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) {
        this.repuestoService = repuestoService;
    }

    @GetMapping
    public ResponseEntity<List<Repuesto>> listarRepuestos() {
        return ResponseEntity.ok(repuestoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> obtenerRepuestoPorId(@PathVariable Long id) {
        return repuestoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
