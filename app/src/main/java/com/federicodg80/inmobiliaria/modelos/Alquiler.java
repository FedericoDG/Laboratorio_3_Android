package com.federicodg80.inmobiliaria.modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Alquiler {
    private int idAlquiler;
    private double precio;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<Pago> pagos;

    public int getIdAlquiler() { return idAlquiler; }

    public double getPrecio() { return precio; }

    public List<Pago> getPagos() { return pagos; }

    public String getFechaInicioFormatted() {
        if (fechaInicio == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaInicio.format(formatter);
    }

    public String getFechaFinFormatted() {
        if (fechaFin == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaFin.format(formatter);
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "idAlquiler=" + idAlquiler +
                ", precio=" + precio +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", pagos=" + pagos +
                '}';
    }
}
