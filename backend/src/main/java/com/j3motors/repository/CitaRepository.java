package com.j3motors.repository;

import com.j3motors.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByEstado(String estado);
    List<Cita> findByPlaca(String placa);
}
