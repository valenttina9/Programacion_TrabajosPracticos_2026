package ar.edu.unlar.programacion3.model;

import java.time.LocalDateTime;

public class Transaccion {
    private TransaccionTipo tipo;
    private double monto;
    private LocalDateTime fechaHora;
    private String descripcion;

    public Transaccion(TransaccionTipo tipo, double monto, LocalDateTime fechaHora, String descripcion) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "tipo=" + tipo +
                ", monto=" + monto +
                ", fechaHora=" + fechaHora +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
