package com.j3motors.service;

import com.j3motors.model.Servicio;
import com.j3motors.repository.ServicioRepository;
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
class ServicioServiceTest {

    @Mock
    private ServicioRepository servicioRepository;

    @InjectMocks
    private ServicioService servicioService;

    private Servicio servicioEjemplo;

    @BeforeEach
    void setUp() {
        servicioEjemplo = new Servicio(
                1L,
                "Mantenimiento General Preventivo",
                "Revisión completa de 25 puntos",
                25.0,
                "2 - 3 Horas",
                "bi-wrench-adjustable"
        );
    }

    @Test
    @DisplayName("Debe listar todos los servicios ofrecidos")
    void testObtenerTodos() {
        when(servicioRepository.findAll()).thenReturn(List.of(servicioEjemplo));

        List<Servicio> resultado = servicioService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Mantenimiento General Preventivo", resultado.get(0).getNombre());
        verify(servicioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener un servicio por su ID")
    void testObtenerPorId_Existente() {
        when(servicioRepository.findById(1L)).thenReturn(Optional.of(servicioEjemplo));

        Optional<Servicio> resultado = servicioService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(servicioRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe guardar un servicio correctamente")
    void testGuardarServicio_Valido() {
        when(servicioRepository.save(any(Servicio.class))).thenReturn(servicioEjemplo);

        Servicio guardado = servicioService.guardar(servicioEjemplo);

        assertNotNull(guardado);
        assertEquals("Mantenimiento General Preventivo", guardado.getNombre());
        verify(servicioRepository, times(1)).save(servicioEjemplo);
    }

    @Test
    @DisplayName("Debe lanzar excepción si el servicio no tiene nombre")
    void testGuardarServicio_SinNombre_LanzaExcepcion() {
        servicioEjemplo.setNombre("   ");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicioService.guardar(servicioEjemplo)
        );

        assertEquals("El nombre del servicio es obligatorio.", ex.getMessage());
        verify(servicioRepository, never()).save(any());
    }
}
