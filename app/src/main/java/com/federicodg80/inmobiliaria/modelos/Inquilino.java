package com.federicodg80.inmobiliaria.modelos;

public class Inquilino {
    private int idInquilino;
    private int dni;
    private String apellido;
    private String nombre;
    private String direccion;
    private int telefono;

    public Inquilino() {}

    public Inquilino(int idInquilino, int dni, String apellido, String nombre, String direccion, int telefono) {
        this.idInquilino = idInquilino;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdInquilino() { return idInquilino; }

    public int getDni() { return dni; }

    public String getApellido() { return apellido; }

    public String getNombre() { return nombre; }

    public String getDireccion() { return direccion; }

    public int getTelefono() { return telefono; }

    @Override
    public String toString() {
        return "Inquilino{" +
                "idInquilino=" + idInquilino +
                ", dni=" + dni +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
