package com.sosa.trabajofinalsosagaston.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Contrato implements Serializable {

    private int id;
    private String fechaDesde;
    private String fechaHasta;
    private String fechaCancelado;
    private double precio;
    private int estado;
    private int idInmueble;
    private int idGarante;
    private int idInquilino;
    private Object garante;
    private Inquilino inquilino;
    private Inmueble inmueble;

    public Contrato() {}
    public Contrato(int idContrato, String fechaInicio, String fechaFin, double montoAlquiler, Inquilino inquilino, Inmueble inmueble) {
        this.id = idContrato;
        this.fechaDesde = fechaInicio;
        this.fechaHasta = fechaFin;
        this.precio = montoAlquiler;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public int getIdContrato() {
        return id;
    }

    public void setIdContrato(int idContrato) {
        this.id = idContrato;
    }

    public String getFechaInicio() {
        return fechaDesde;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaDesde = fechaInicio;
    }

    public String getFechaFin() {
        return fechaHasta;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaHasta = fechaFin;
    }

    public double getMontoAlquiler() {
        return precio;
    }

    public void setMontoAlquiler(double montoAlquiler) {
        this.precio = montoAlquiler;
    }


    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return id == contrato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
