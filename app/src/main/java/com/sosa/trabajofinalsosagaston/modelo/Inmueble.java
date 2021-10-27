package com.sosa.trabajofinalsosagaston.modelo;

import static com.sosa.trabajofinalsosagaston.request.ApiClient.UrlBase;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;
    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.
    private int estado;
    private String imagen;
    private int ambiente;
    private int latitud;
    private int longitud;
    private Propietario duenio;
    private double precio;
    private int superficie;
    private int idPropietario;
    private String imagenGuardar;

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getEstado() {
        return estado;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagenGuardar() {
        return imagenGuardar;
    }

    public void setImagenGuardar(String imagenGuardar) {
        this.imagenGuardar = imagenGuardar;
    }




    public Inmueble(int id, String direccion, int ambientes, int latitud, int longitud, Propietario duenio, double precio, int idpropietario, int estado) {
        this.id = id;
        this.direccion = direccion;
        this.ambiente = ambientes;
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


        this.ambiente = ambientes;
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
        return ambiente;
    }

    public void setAmbientes(int ambientes) {
        this.ambiente = ambientes;
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
        if(imagen==null){
            return "http://www.secsanluis.com.ar/servicios/salon1.jpg";
        }
        return "http://192.169.1.4:45455/"+imagen.replace("\\", "/");


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

    @Override
    public String toString() {
        return "Inmueble{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", estado=" + estado +
                ", imagen='" + imagen + '\'' +
                ", ambiente=" + ambiente +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", duenio=" + duenio +
                ", precio=" + precio +
                ", superficie=" + superficie +
                ", idPropietario=" + idPropietario +
                ", imagenGuardar=" + imagenGuardar +
                '}';
    }
}
