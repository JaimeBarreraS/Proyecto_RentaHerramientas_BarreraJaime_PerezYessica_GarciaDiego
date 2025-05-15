package com.proyecto.domain.entities;


import jakarta.persistence.Entity;

@Entity
public class Proveedor extends Personas {
    private String rut;
    private String Empresa;

    public Proveedor() {
    }

    public Proveedor(Long persona_ID, String nombre, String apellido, String correo, String telefono, String direccion, Usuarios usuario, String rut, String empresa) {
        super(persona_ID, nombre, apellido, correo, telefono, direccion, usuario);
        this.rut = rut;
        Empresa = empresa;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    
}
