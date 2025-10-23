package com.federicodg80.inmobiliaria.api.propietario;

import com.federicodg80.inmobiliaria.modelos.Propietario;

public class PropietarioUpdateRequest extends Propietario {
    String oldPassword;

    public PropietarioUpdateRequest(int idPropietario, int dni, String apellido, String nombre, int telefono, String mail, String password, String oldPassword) {
        super(idPropietario, dni, apellido, nombre, telefono, mail, password);
        this.oldPassword = oldPassword;
    }
}
