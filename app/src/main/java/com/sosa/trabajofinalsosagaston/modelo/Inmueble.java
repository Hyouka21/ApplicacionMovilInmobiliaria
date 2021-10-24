package com.sosa.trabajofinalsosagaston.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;


    private int ambientes;
    private int latitud;
    private int longitud;
    private Propietario duenio;
    private double precio;
    private int idPropietario;
    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.
    private int estado=0;


    public Inmueble(int id, String direccion, int ambientes, int latitud, int longitud, Propietario duenio, double precio, int idpropietario, int estado) {
        this.id = id;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.latitud = latitud;
        this.longitud = longitud;
        this.duenio = duenio;
        this.precio = precio;
        this.idPropietario = idpropietario;
        this.estado = estado;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }

    public Inmueble(int id, String direccion, int ambientes, double precio, int propietario, int estado) {
        this.id = id;
        this.direccion = direccion;


        this.ambientes = ambientes;
        this.precio = precio;
        this.idPropietario = propietario;
        this.estado = estado;

    }
    public Inmueble() {

    }
    public int getIdInmueble() {
        return id;
    }

    public void setIdInmueble(int idInmueble) {
        this.id = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return "particular";
    }


    public String getTipo() {
        return "casa";
    }



    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdpropietario() {
        return idPropietario;
    }

    public void setIdpropietario(int idpropietario) {
        this.idPropietario = idpropietario;
    }

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return "http://www.secsanluis.com.ar/servicios/salon1.jpg";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
