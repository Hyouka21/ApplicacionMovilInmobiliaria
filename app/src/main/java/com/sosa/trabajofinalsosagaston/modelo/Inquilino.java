package com.sosa.trabajofinalsosagaston.modelo;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int idInquilino;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;



    public Inquilino() {}



    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public Long getDNI() {
        return Long.parseLong(dni);
    }

    public void setDNI(Long DNI) {
        this.dni = DNI.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreGarante() {
        return "Gastonsito te lo garantiza";
    }



    public String getTelefonoGarante() {
        return "111222";
    }


}
