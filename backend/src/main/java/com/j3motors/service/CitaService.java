package com.j3motors.service;

import com.j3motors.model.Cita;
import com.j3motors.repository.CitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Transactional(readOnly = true)
    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Cita> obtenerPorId(Long id) {
        return citaRepository.findById(id);
    }

    public Cita registrarCita(Cita nuevaCita) {
        if (nuevaCita.getNombreCliente() == null || nuevaCita.getNombreCliente().isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
        }
        if (nuevaCita.getTelefono() == null || nuevaCita.getTelefono().isBlank()) {
            throw new IllegalArgumentException("El teléfono es obligatorio.");
        }
        if (nuevaCita.getPlaca() == null || nuevaCita.getPlaca().isBlank()) {
            throw new IllegalArgumentException("La placa de la motocicleta es obligatoria.");
        }
        if (nuevaCita.getEstado() == null || nuevaCita.getEstado().isBlank()) {
            nuevaCita.setEstado("PENDIENTE");
        }
        return citaRepository.save(nuevaCita);
    }

    public Cita actualizarEstado(Long id, String nuevoEstado) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada con id: " + id));
        cita.setEstado(nuevoEstado);
        return citaRepository.save(cita);
    }

    public void eliminarCita(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new IllegalArgumentException("Cita no encontrada con id: " + id);
        }
        citaRepository.deleteById(id);
    }
}
