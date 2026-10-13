package com.j3motors.service;

import com.j3motors.model.Repuesto;
import com.j3motors.repository.RepuestoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RepuestoService {

    private final RepuestoRepository repuestoRepository;

    public RepuestoService(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @Transactional(readOnly = true)
    public List<Repuesto> obtenerTodos() {
        return repuestoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Repuesto> obtenerPorId(Long id) {
        return repuestoRepository.findById(id);
    }

    public Repuesto guardar(Repuesto repuesto) {
        if (repuesto.getNombre() == null || repuesto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del repuesto es obligatorio.");
        }
        if (repuesto.getPrecio() == null || repuesto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        if (repuesto.getStock() == null || repuesto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        return repuestoRepository.save(repuesto);
    }

    public Repuesto actualizarStock(Long id, int cantidad) {
        Repuesto repuesto = repuestoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Repuesto no encontrado con id: " + id));
        if (repuesto.getStock() + cantidad < 0) {
            throw new IllegalArgumentException("Stock insuficiente para el repuesto: " + repuesto.getNombre());
        }
        repuesto.setStock(repuesto.getStock() + cantidad);
        return repuestoRepository.save(repuesto);
    }

    public void eliminar(Long id) {
        if (!repuestoRepository.existsById(id)) {
            throw new IllegalArgumentException("Repuesto no encontrado con id: " + id);
        }
        repuestoRepository.deleteById(id);
    }
}
