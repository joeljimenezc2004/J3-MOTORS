package com.j3motors.model;

import java.time.LocalDateTime;

public class Cita {
    private Long id;
    private String nombreCliente;
    private String telefono;
    private String email;
    private String modeloMoto;
    private String placa;
    private String tipoServicio;
    private String fechaCita;
    private String horaCita;
    private String observaciones;
    private String estado; // PENDIENTE, CONFIRMADA, FINALIZADA
    private LocalDateTime fechaCreacion;

    public Cita() {
        this.fechaCreacion = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    public Cita(Long id, String nombreCliente, String telefono, String email, String modeloMoto, String placa,
                String tipoServicio, String fechaCita, String horaCita, String observaciones) {
        this();
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.email = email;
        this.modeloMoto = modeloMoto;
        this.placa = placa;
        this.tipoServicio = tipoServicio;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.observaciones = observaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModeloMoto() {
        return modeloMoto;
    }

    public void setModeloMoto(String modeloMoto) {
        this.modeloMoto = modeloMoto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
