package com.j3motors.service;

import com.j3motors.model.Servicio;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

        private final List<Servicio> servicios = new ArrayList<>();

        @PostConstruct
        public void initData() {
                servicios.add(new Servicio(
                                1L,
                                "Mantenimiento General Preventivo",
                                "Revisión completa de 25 puntos: ajuste de tornillería, lubricación de guayas, calibración de bujía, limpieza de filtro de aire y revisión de niveles.",
                                25.0,
                                "2 - 3 Horas",
                                "bi-wrench-adjustable"));
                servicios.add(new Servicio(
                                2L,
                                "Sincronización y Diagnóstico Computarizado",
                                "Escaneo electrónico del sistema EFI / Inyección electrónica, calibración de TPS, limpieza de inyector por ultrasonido y cuerpo de aceleración.",
                                45.0,
                                "1.5 Horas",
                                "bi-cpu"));
                servicios.add(new Servicio(
                                3L,
                                "Sistema de Frenos y Suspensión",
                                "Cambio de líquido de frenos DOT4/DOT5.1, purgado, cambio de pastillas/bandas, cambio de retenedores y aceite hidráulico de suspensión delantera.",
                                28.0,
                                "2 Horas",
                                "bi-shield-check"));
                servicios.add(new Servicio(
                                4L,
                                "Reparación Mayor de Motor y Transmisión",
                                "Ajuste de válvulas, cambio de discos de embrague/clutch, rectificación de cilindro, pistón, anillos y empacado completo con torquímetro.",
                                120.0,
                                "1 - 2 Días",
                                "bi-gear-wide-connected"));
                servicios.add(new Servicio(
                                5L,
                                "Sistema Eléctrico y Batería",
                                "Diagnóstico de estator, regulador/rectificador, reemplazo y carga de batería, instalación de luces LED exploradoras y alarmas.",
                                25.0,
                                "1 Hora",
                                "bi-lightning-charge"));
                servicios.add(new Servicio(
                                6L,
                                "Cambio de Kit de Arrastre y Neumáticos",
                                "Instalación y alineación de piñón de salida, corona y cadena reforzada con o-ring, calibración y montaje de llantas tubeless.",
                                22.0,
                                "1 Hora",
                                "bi-arrow-repeat"));
        }

        public List<Servicio> obtenerTodos() {
                return new ArrayList<>(servicios);
        }

        public Optional<Servicio> obtenerPorId(Long id) {
                return servicios.stream().filter(s -> s.getId().equals(id)).findFirst();
        }
}
