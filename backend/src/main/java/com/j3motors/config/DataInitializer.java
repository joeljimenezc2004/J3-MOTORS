package com.j3motors.config;

import com.j3motors.model.Cita;
import com.j3motors.model.Repuesto;
import com.j3motors.model.Rol;
import com.j3motors.model.Servicio;
import com.j3motors.model.Usuario;
import com.j3motors.repository.CitaRepository;
import com.j3motors.repository.RepuestoRepository;
import com.j3motors.repository.ServicioRepository;
import com.j3motors.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final UsuarioRepository usuarioRepository;
    private final ServicioRepository servicioRepository;
    private final RepuestoRepository repuestoRepository;
    private final CitaRepository citaRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository,
                           ServicioRepository servicioRepository,
                           RepuestoRepository repuestoRepository,
                           CitaRepository citaRepository,
                           PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.servicioRepository = servicioRepository;
        this.repuestoRepository = repuestoRepository;
        this.citaRepository = citaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        inicializarUsuarios();
        inicializarServicios();
        inicializarRepuestos();
        inicializarCitas();
    }

    private void inicializarUsuarios() {
        if (usuarioRepository.count() == 0) {
            logger.info("Inicializando usuarios por defecto en la base de datos...");

            Usuario admin = new Usuario(
                    null,
                    "admin",
                    passwordEncoder.encode("admin123"),
                    "Administrador J3 Motors",
                    "admin@j3motors.com",
                    Set.of(Rol.ROLE_ADMIN, Rol.ROLE_TECNICO)
            );

            Usuario tecnico = new Usuario(
                    null,
                    "tecnico",
                    passwordEncoder.encode("tecnico123"),
                    "Javier Mecánico",
                    "tecnico@j3motors.com",
                    Set.of(Rol.ROLE_TECNICO)
            );

            Usuario cliente = new Usuario(
                    null,
                    "cliente",
                    passwordEncoder.encode("cliente123"),
                    "Carlos Mendoza",
                    "cliente@j3motors.com",
                    Set.of(Rol.ROLE_CLIENTE)
            );

            usuarioRepository.saveAll(List.of(admin, tecnico, cliente));
            logger.info("Usuarios creados: admin (admin123), tecnico (tecnico123), cliente (cliente123)");
        }
    }

    private void inicializarServicios() {
        if (servicioRepository.count() == 0) {
            logger.info("Inicializando catálogo de servicios...");
            servicioRepository.saveAll(List.of(
                    new Servicio(
                            null,
                            "Mantenimiento General Preventivo",
                            "Revisión completa de 25 puntos: ajuste de tornillería, lubricación de guayas, calibración de bujía, limpieza de filtro de aire y revisión de niveles.",
                            25.0,
                            "2 - 3 Horas",
                            "bi-wrench-adjustable"),
                    new Servicio(
                            null,
                            "Sincronización y Diagnóstico Computarizado",
                            "Escaneo electrónico del sistema EFI / Inyección electrónica, calibración de TPS, limpieza de inyector por ultrasonido y cuerpo de aceleración.",
                            45.0,
                            "1.5 Horas",
                            "bi-cpu"),
                    new Servicio(
                            null,
                            "Sistema de Frenos y Suspensión",
                            "Cambio de líquido de frenos DOT4/DOT5.1, purgado, cambio de pastillas/bandas, cambio de retenedores y aceite hidráulico de suspensión delantera.",
                            28.0,
                            "2 Horas",
                            "bi-shield-check"),
                    new Servicio(
                            null,
                            "Reparación Mayor de Motor y Transmisión",
                            "Ajuste de válvulas, cambio de discos de embrague/clutch, rectificación de cilindro, pistón, anillos y empacado completo con torquímetro.",
                            120.0,
                            "1 - 2 Días",
                            "bi-gear-wide-connected"),
                    new Servicio(
                            null,
                            "Sistema Eléctrico y Batería",
                            "Diagnóstico de estator, regulador/rectificador, reemplazo y carga de batería, instalación de luces LED exploradoras y alarmas.",
                            25.0,
                            "1 Hora",
                            "bi-lightning-charge"),
                    new Servicio(
                            null,
                            "Cambio de Kit de Arrastre y Neumáticos",
                            "Instalación y alineación de piñón de salida, corona y cadena reforzada con o-ring, calibración y montaje de llantas tubeless.",
                            22.0,
                            "1 Hora",
                            "bi-arrow-repeat")
            ));
        }
    }

    private void inicializarRepuestos() {
        if (repuestoRepository.count() == 0) {
            logger.info("Inicializando catálogo de repuestos...");
            repuestoRepository.saveAll(List.of(
                    new Repuesto(
                            null,
                            "Aceite Motul 7100 4T 10W-40 100% Sintético",
                            "Lubricantes",
                            "Motul",
                            19.5,
                            24,
                            "https://images.unsplash.com/photo-1596765851458-1f19f180702d?auto=format&fit=crop&w=400&q=80",
                            "Universal 4 Tiempos (Alto Rendimiento)"
                    ),
                    new Repuesto(
                            null,
                            "Pastillas de Freno Sinterizadas Brembo Serie Oro",
                            "Frenos",
                            "Brembo",
                            48.0,
                            15,
                            "https://images.unsplash.com/photo-1619642751034-765dfdf7c58e?auto=format&fit=crop&w=400&q=80",
                            "Yamaha MT-03, MT-07, R3, Kawasaki Z400"
                    ),
                    new Repuesto(
                            null,
                            "Kit de Arrastre DID Cadena Dorada X-Ring 520",
                            "Transmisión",
                            "DID / Sunstar",
                            89.9,
                            8,
                            "https://images.unsplash.com/photo-1558981403-c5f9899a28bc?auto=format&fit=crop&w=400&q=80",
                            "KTM Duke 200/250/390, Pulsar NS200"
                    ),
                    new Repuesto(
                            null,
                            "Bujía Iridium IX NGK CR9EIX",
                            "Encendido",
                            "NGK",
                            14.0,
                            32,
                            "https://images.unsplash.com/photo-1486006920555-c77dce18193b?auto=format&fit=crop&w=400&q=80",
                            "Universal Alto Rendimiento"
                    ),
                    new Repuesto(
                            null,
                            "Filtro de Aceite K&N Rendimiento Pro",
                            "Filtros",
                            "K&N",
                            16.5,
                            20,
                            "https://images.unsplash.com/photo-1616788494707-ec28f08d05a1?auto=format&fit=crop&w=400&q=80",
                            "Kawasaki Ninja/Z, Honda CBR"
                    ),
                    new Repuesto(
                            null,
                            "Llantas Michelin Pilot Street 2 (140/70-17)",
                            "Neumáticos",
                            "Michelin",
                            82.0,
                            6,
                            "https://images.unsplash.com/photo-1578844251758-2f71da64c96f?auto=format&fit=crop&w=400&q=80",
                            "Rin 17 Trasero Mediano Cilindraje"
                    )
            ));
        }
    }

    private void inicializarCitas() {
        if (citaRepository.count() == 0) {
            logger.info("Inicializando citas iniciales...");
            citaRepository.saveAll(List.of(
                    new Cita(
                            null,
                            "Carlos Mendoza",
                            "+57 310 456 7890",
                            "carlos.mendoza@email.com",
                            "Yamaha MT-07",
                            "ABC-12E",
                            "Mantenimiento General Preventivo",
                            "2026-09-15",
                            "09:00",
                            "Revisar sonido en cadena y cambio de aceite sintético 10W-40."
                    ),
                    new Cita(
                            null,
                            "Valeria Ramírez",
                            "+57 320 890 1234",
                            "valeria.r@email.com",
                            "KTM Duke 390",
                            "XYZ-89F",
                            "Sincronización y Diagnóstico Computarizado",
                            "2026-09-16",
                            "11:30",
                            "Testigo de Check Engine encendido intermitente."
                    ),
                    new Cita(
                            null,
                            "Andrés Gómez",
                            "+57 301 234 5678",
                            "andres.gomez@email.com",
                            "Kawasaki Ninja 400",
                            "JKD-34D",
                            "Sistema de Frenos y Suspensión",
                            "2026-09-18",
                            "14:00",
                            "Purgado de líquido de freno y pastillas sinterizadas."
                    )
            ));
        }
    }
}
