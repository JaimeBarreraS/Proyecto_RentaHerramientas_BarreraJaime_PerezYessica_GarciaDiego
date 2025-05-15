package com.proyecto.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HerramientaEquipo")
public class HerramientaEquipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long herramienta_ID;
    private String nombre;
    private String descripocion;
    private String tipo;
    private String marca;
    private Long serial;
    private String estado;
    private String Img_url;
    private String disponivilidad;
    private int cantidad;
    private String precioPorDia;
    private String precioPorHora;

    @ManyToOne
    private Proveedor proveedor;

    public HerramientaEquipo() {
    }

    public HerramientaEquipo(Long herramienta_ID, String nombre, String descripocion, String tipo, String marca,
            Long serial, String estado, String img_url, String disponivilidad, int cantidad, String precioPorDia,
            String precioPorHora) {
        this.herramienta_ID = herramienta_ID;
        this.nombre = nombre;
        this.descripocion = descripocion;
        this.tipo = tipo;
        this.marca = marca;
        this.serial = serial;
        this.estado = estado;
        Img_url = img_url;
        this.disponivilidad = disponivilidad;
        this.cantidad = cantidad;
        this.precioPorDia = precioPorDia;
        this.precioPorHora = precioPorHora;
    }

    public Long getHerramienta_ID() {
        return herramienta_ID;
    }

    public void setHerramienta_ID(Long herramienta_ID) {
        this.herramienta_ID = herramienta_ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripocion() {
        return descripocion;
    }

    public void setDescripocion(String descripocion) {
        this.descripocion = descripocion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImg_url() {
        return Img_url;
    }

    public void setImg_url(String img_url) {
        Img_url = img_url;
    }

    public String getDisponivilidad() {
        return disponivilidad;
    }

    public void setDisponivilidad(String disponivilidad) {
        this.disponivilidad = disponivilidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(String precioPorDia) {
        this.precioPorDia = precioPorDia;
    }

    public String getPrecioPorHora() {
        return precioPorHora;
    }

    public void setPrecioPorHora(String precioPorHora) {
        this.precioPorHora = precioPorHora;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    

}
