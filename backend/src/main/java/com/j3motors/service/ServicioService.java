package com.j3motors.service;

import com.j3motors.model.Servicio;
import com.j3motors.repository.ServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Transactional(readOnly = true)
    public List<Servicio> obtenerTodos() {
        return servicioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Servicio> obtenerPorId(Long id) {
        return servicioRepository.findById(id);
    }

    public Servicio guardar(Servicio servicio) {
        if (servicio.getNombre() == null || servicio.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del servicio es obligatorio.");
        }
        if (servicio.getPrecioAproximado() == null || servicio.getPrecioAproximado() <= 0) {
            throw new IllegalArgumentException("El precio aproximado debe ser mayor a 0.");
        }
        return servicioRepository.save(servicio);
    }

    public void eliminar(Long id) {
        if (!servicioRepository.existsById(id)) {
            throw new IllegalArgumentException("Servicio no encontrado con id: " + id);
        }
        servicioRepository.deleteById(id);
    }
}
