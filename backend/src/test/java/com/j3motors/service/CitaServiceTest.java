package com.j3motors.service;

import com.j3motors.model.Cita;
import com.j3motors.repository.CitaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private CitaService citaService;

    private Cita citaEjemplo;

    @BeforeEach
    void setUp() {
        citaEjemplo = new Cita(
                1L,
                "Juan Pérez",
                "+51 987654321",
                "juan@correo.com",
                "Yamaha MT-03",
                "1234-5A",
                "Mantenimiento General Preventivo",
                "2026-10-20",
                "10:00",
                "Cambio de aceite y filtro"
        );
    }

    @Test
    @DisplayName("Debe listar todas las citas registradas")
    void testObtenerTodas() {
        when(citaRepository.findAll()).thenReturn(List.of(citaEjemplo));

        List<Cita> resultado = citaService.obtenerTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombreCliente());
        verify(citaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe registrar una cita exitosamente con estado CONFIRMADA por defecto")
    void testRegistrarCitaExitosa() {
        when(citaRepository.save(any(Cita.class))).thenReturn(citaEjemplo);

        Cita nuevaCita = new Cita(
                null,
                "Juan Pérez",
                "+51 987654321",
                "juan@correo.com",
                "Yamaha MT-03",
                "1234-5A",
                "Mantenimiento General Preventivo",
                "2026-10-20",
                "10:00",
                "Cambio de aceite"
        );

        Cita resultado = citaService.registrarCita(nuevaCita);

        assertNotNull(resultado);
        assertEquals("PENDIENTE", nuevaCita.getEstado());
        verify(citaRepository, times(1)).save(nuevaCita);
    }

    @Test
    @DisplayName("Debe lanzar excepción al registrar cita sin nombre de cliente")
    void testRegistrarCitaSinNombreCliente_DebeLanzarExcepcion() {
        citaEjemplo.setNombreCliente(null);

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> citaService.registrarCita(citaEjemplo)
        );

        assertEquals("El nombre del cliente es obligatorio.", excepcion.getMessage());
        verify(citaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción al registrar cita sin teléfono")
    void testRegistrarCitaSinTelefono_DebeLanzarExcepcion() {
        citaEjemplo.setTelefono(" ");

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> citaService.registrarCita(citaEjemplo)
        );

        assertEquals("El teléfono es obligatorio.", excepcion.getMessage());
        verify(citaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe actualizar el estado de una cita existente")
    void testActualizarEstado_Exitoso() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaEjemplo));
        when(citaRepository.save(any(Cita.class))).thenReturn(citaEjemplo);

        Cita actualizada = citaService.actualizarEstado(1L, "FINALIZADA");

        assertEquals("FINALIZADA", citaEjemplo.getEstado());
        verify(citaRepository, times(1)).findById(1L);
        verify(citaRepository, times(1)).save(citaEjemplo);
    }

    @Test
    @DisplayName("Debe eliminar una cita existente")
    void testEliminarCita_Existente() {
        when(citaRepository.existsById(1L)).thenReturn(true);
        doNothing().when(citaRepository).deleteById(1L);

        assertDoesNotThrow(() -> citaService.eliminarCita(1L));

        verify(citaRepository, times(1)).existsById(1L);
        verify(citaRepository, times(1)).deleteById(1L);
    }
}
