package com.sosa.trabajofinalsosagaston.modelo;

import androidx.annotation.NonNull;

import com.sosa.trabajofinalsosagaston.R;

import java.io.Serializable;
import java.util.Objects;

public class Propietario implements Serializable {

    private int idPropietario;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String clave;
    private String telefono;
    private int avatar;

    public Propietario(){}
    public Propietario(int id, String dni, String nombre, String apellido, String email, String clave, String telefono, int avatar) {
        this.idPropietario = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.clave = clave;
        this.telefono = telefono;
        this.avatar=avatar;
    }

    public int getId() {
        return idPropietario;
    }

    public void setId(int id) {
        this.idPropietario = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getAvatar() {
        if(avatar==0){
            avatar = R.drawable.juan;
        }
        return R.drawable.juan;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Propietario that = (Propietario) o;
        return idPropietario == that.idPropietario;
    }

    @NonNull
    @Override
    public String toString() {
        return getAvatar() + nombre + apellido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPropietario);
    }
}
