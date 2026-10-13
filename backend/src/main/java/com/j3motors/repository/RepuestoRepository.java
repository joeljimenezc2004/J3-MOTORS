package com.j3motors.repository;

import com.j3motors.model.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Long> {
    List<Repuesto> findByCategoriaIgnoreCase(String categoria);
    List<Repuesto> findByNombreContainingIgnoreCase(String nombre);
}
