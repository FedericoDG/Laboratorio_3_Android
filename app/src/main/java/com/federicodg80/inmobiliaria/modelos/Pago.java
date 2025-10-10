package com.federicodg80.inmobiliaria.modelos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pago implements Serializable {
    private int idPago;
    private int nroPago;
    private LocalDateTime fecha;
    private double importe;

    public int getIdPago() { return idPago; }

    public int getNroPago() { return nroPago; }

    public LocalDateTime getFecha() { return fecha; }

    public double getImporte() { return importe; }

    public String getFechaFormatted() {
        if (fecha == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", nroPago=" + nroPago +
                ", fecha=" + fecha +
                ", importe=" + importe +
                '}';
    }
}
