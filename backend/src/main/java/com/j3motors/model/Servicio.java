package com.j3motors.model;

public class Servicio {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precioAproximado;
    private String duracionEstimada;
    private String icono;

    public Servicio() {
    }

    public Servicio(Long id, String nombre, String descripcion, Double precioAproximado, String duracionEstimada, String icono) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioAproximado = precioAproximado;
        this.duracionEstimada = duracionEstimada;
        this.icono = icono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioAproximado() {
        return precioAproximado;
    }

    public void setPrecioAproximado(Double precioAproximado) {
        this.precioAproximado = precioAproximado;
    }

    public String getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(String duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
}
