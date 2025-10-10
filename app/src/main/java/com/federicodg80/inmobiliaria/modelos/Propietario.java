package com.federicodg80.inmobiliaria.modelos;

public class Propietario {
    private int idPropietario;
    private int dni;
    private String apellido;
    private String nombre;
    private int telefono;
    private String mail;
    private String password;

    public Propietario(int idPropietario, int dni, String apellido, String nombre, int telefono, String mail, String password) {
        this.idPropietario = idPropietario;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
        this.password = password;
    }

    public int getIdPropietario() { return idPropietario; }

    public int getDni() { return dni; }

    public String getApellido() { return apellido; }

    public String getNombre() { return nombre; }

    public int getTelefono() { return telefono; }

    public String getMail() { return mail; }

}

