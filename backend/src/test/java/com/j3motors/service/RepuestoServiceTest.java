package com.j3motors.service;

import com.j3motors.model.Repuesto;
import com.j3motors.repository.RepuestoRepository;
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
class RepuestoServiceTest {

    @Mock
    private RepuestoRepository repuestoRepository;

    @InjectMocks
    private RepuestoService repuestoService;

    private Repuesto repuestoEjemplo;

    @BeforeEach
    void setUp() {
        repuestoEjemplo = new Repuesto(
                1L,
                "Aceite Motul 7100 4T",
                "Lubricantes",
                "Motul",
                20.0,
                10,
                "http://img.com/aceite.jpg",
                "Universal"
        );
    }

    @Test
    @DisplayName("Debe listar todos los repuestos")
    void testObtenerTodos() {
        when(repuestoRepository.findAll()).thenReturn(List.of(repuestoEjemplo));

        List<Repuesto> resultado = repuestoService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Aceite Motul 7100 4T", resultado.get(0).getNombre());
        verify(repuestoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe guardar un repuesto válido")
    void testGuardarRepuesto_Valido() {
        when(repuestoRepository.save(any(Repuesto.class))).thenReturn(repuestoEjemplo);

        Repuesto guardado = repuestoService.guardar(repuestoEjemplo);

        assertNotNull(guardado);
        assertEquals("Aceite Motul 7100 4T", guardado.getNombre());
        verify(repuestoRepository, times(1)).save(repuestoEjemplo);
    }

    @Test
    @DisplayName("Debe lanzar excepción si el precio del repuesto es cero o negativo")
    void testGuardarRepuesto_PrecioInvalido_LanzaExcepcion() {
        repuestoEjemplo.setPrecio(0.0);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> repuestoService.guardar(repuestoEjemplo)
        );

        assertEquals("El precio debe ser mayor a 0.", ex.getMessage());
        verify(repuestoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe actualizar el stock exitosamente")
    void testActualizarStock_Exitoso() {
        when(repuestoRepository.findById(1L)).thenReturn(Optional.of(repuestoEjemplo));
        when(repuestoRepository.save(any(Repuesto.class))).thenReturn(repuestoEjemplo);

        Repuesto actualizado = repuestoService.actualizarStock(1L, 5);

        assertEquals(15, actualizado.getStock());
        verify(repuestoRepository, times(1)).findById(1L);
        verify(repuestoRepository, times(1)).save(repuestoEjemplo);
    }

    @Test
    @DisplayName("Debe lanzar excepción si la reducción de stock excede la disponibilidad")
    void testActualizarStock_StockInsuficiente_LanzaExcepcion() {
        when(repuestoRepository.findById(1L)).thenReturn(Optional.of(repuestoEjemplo));

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> repuestoService.actualizarStock(1L, -15)
        );

        assertTrue(ex.getMessage().contains("Stock insuficiente"));
        verify(repuestoRepository, never()).save(any());
    }
}
