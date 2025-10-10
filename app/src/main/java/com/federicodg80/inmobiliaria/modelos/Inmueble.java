package com.federicodg80.inmobiliaria.modelos;

import java.io.Serializable;

public class Inmueble implements Serializable {
    private int idInmueble;
    private String direccion;
    private int ambientes;
    private String tipo;
    private String uso;
    private double precio;
    private String imagen;
    private boolean disponible;
    private Propietario propietario;
    private Inquilino inquilino;
    private Alquiler alquiler;

    public Inmueble() {}

    public Inmueble(int idInmueble, String direccion, int ambientes, String tipo, String uso, double precio, String imagen, boolean disponible) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.tipo = tipo;
        this.uso = uso;
        this.precio = precio;
        this.imagen = imagen;
        this.disponible = disponible;
    }

    public Inmueble(int idInmueble, String direccion, int ambientes, String tipo, String uso, double precio, String imagen, boolean disponible, Propietario propietario) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.tipo = tipo;
        this.uso = uso;
        this.precio = precio;
        this.imagen = imagen;
        this.disponible = disponible;
        this.propietario = propietario;
    }

    public Inmueble(int idInmueble, String direccion, int ambientes, String tipo, String uso, double precio, String imagen, boolean disponible, Inquilino inquilino) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.tipo = tipo;
        this.uso = uso;
        this.precio = precio;
        this.imagen = imagen;
        this.disponible = disponible;
        this.inquilino = inquilino;
    }

    public Inmueble(int idInmueble, String direccion, int ambientes, String tipo, String uso, double precio, String imagen, boolean disponible, Alquiler alquiler) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.tipo = tipo;
        this.uso = uso;
        this.precio = precio;
        this.imagen = imagen;
        this.disponible = disponible;
        this.alquiler = alquiler;
    }

    public int getIdInmueble() { return idInmueble; }

    public String getDireccion() { return direccion; }

    public int getAmbientes() { return ambientes; }

    public String getTipo() { return tipo; }

    public String getUso() { return uso; }

    public double getPrecio() { return precio; }

    public boolean isDisponible() { return disponible; }

    public Inquilino getInquilino() { return inquilino; }

    public Alquiler getAlquiler() { return alquiler; }

    public String getImagen() { return imagen; }

    @Override
    public String toString() {
        return "Inmueble{" +
                "idInmueble=" + idInmueble +
                ", direccion='" + direccion + '\'' +
                ", ambientes=" + ambientes +
                ", tipo='" + tipo + '\'' +
                ", uso='" + uso + '\'' +
                ", precio=" + precio +
                ", imagen='" + imagen + '\'' +
                ", disponible=" + disponible +
                ", propietario=" + propietario +
                ", inquilino=" + inquilino +
                ", alquiler=" + alquiler +
                '}';
    }
}
