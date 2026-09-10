package com.j3motors.service;

import com.j3motors.model.Repuesto;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepuestoService {

    private final List<Repuesto> repuestos = new ArrayList<>();

    @PostConstruct
    public void initData() {
        repuestos.add(new Repuesto(
                1L,
                "Aceite Motul 7100 4T 10W-40 100% Sintético",
                "Lubricantes",
                "Motul",
                19.5,
                24,
                "https://images.unsplash.com/photo-1596765851458-1f19f180702d?auto=format&fit=crop&w=400&q=80",
                "Universal 4 Tiempos (Alto Rendimiento)"
        ));
        repuestos.add(new Repuesto(
                2L,
                "Pastillas de Freno Sinterizadas Brembo Serie Oro",
                "Frenos",
                "Brembo",
                48.0,
                15,
                "https://images.unsplash.com/photo-1619642751034-765dfdf7c58e?auto=format&fit=crop&w=400&q=80",
                "Yamaha MT-03, MT-07, R3, Kawasaki Z400"
        ));
        repuestos.add(new Repuesto(
                3L,
                "Kit de Arrastre DID Cadena Dorada X-Ring 520",
                "Transmisión",
                "DID / Sunstar",
                89.9,
                8,
                "https://images.unsplash.com/photo-1558981403-c5f9899a28bc?auto=format&fit=crop&w=400&q=80",
                "KTM Duke 200/250/390, Pulsar NS200"
        ));
        repuestos.add(new Repuesto(
                4L,
                "Bujía Iridium IX NGK CR9EIX",
                "Encendido",
                "NGK",
                14.0,
                32,
                "https://images.unsplash.com/photo-1486006920555-c77dce18193b?auto=format&fit=crop&w=400&q=80",
                "Universal Alto Rendimiento"
        ));
        repuestos.add(new Repuesto(
                5L,
                "Filtro de Aceite K&N Rendimiento Pro",
                "Filtros",
                "K&N",
                16.5,
                20,
                "https://images.unsplash.com/photo-1616788494707-ec28f08d05a1?auto=format&fit=crop&w=400&q=80",
                "Kawasaki Ninja/Z, Honda CBR"
        ));
        repuestos.add(new Repuesto(
                6L,
                "Llantas Michelin Pilot Street 2 (140/70-17)",
                "Neumáticos",
                "Michelin",
                82.0,
                6,
                "https://images.unsplash.com/photo-1578844251758-2f71da64c96f?auto=format&fit=crop&w=400&q=80",
                "Rin 17 Trasero Mediano Cilindraje"
        ));
    }

    public List<Repuesto> obtenerTodos() {
        return new ArrayList<>(repuestos);
    }

    public Optional<Repuesto> obtenerPorId(Long id) {
        return repuestos.stream().filter(r -> r.getId().equals(id)).findFirst();
    }
}
