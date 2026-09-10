package com.j3motors.service;

import com.j3motors.model.Cita;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CitaService {

    private final List<Cita> citas = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void initData() {
        registrarCita(new Cita(
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
        ));

        registrarCita(new Cita(
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
        ));

        registrarCita(new Cita(
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
        ));
    }

    public List<Cita> obtenerTodas() {
        synchronized (citas) {
            return new ArrayList<>(citas);
        }
    }

    public Cita registrarCita(Cita nuevaCita) {
        nuevaCita.setId(idGenerator.getAndIncrement());
        if (nuevaCita.getEstado() == null || nuevaCita.getEstado().isBlank()) {
            nuevaCita.setEstado("CONFIRMADA");
        }
        citas.add(nuevaCita);
        return nuevaCita;
    }
}
