package com.j3motors.controller;

import com.j3motors.model.Repuesto;
import com.j3motors.service.RepuestoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
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

    @PostMapping
    public ResponseEntity<Repuesto> crearRepuesto(@RequestBody Repuesto repuesto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repuestoService.guardar(repuesto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repuesto> actualizarRepuesto(@PathVariable Long id, @RequestBody Repuesto repuesto) {
        repuesto.setId(id);
        return ResponseEntity.ok(repuestoService.guardar(repuesto));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Repuesto> actualizarStock(@PathVariable Long id, @RequestParam int cantidad) {
        return ResponseEntity.ok(repuestoService.actualizarStock(id, cantidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRepuesto(@PathVariable Long id) {
        repuestoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
