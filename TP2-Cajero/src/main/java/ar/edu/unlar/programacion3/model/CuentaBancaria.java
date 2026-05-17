package ar.edu.unlar.programacion3.model;

import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {
    private final String numeroCuenta; // inmutable
    private double saldo;
    private String titular;
    private boolean activa;
    private List<Transaccion> historialTransacciones;

    public CuentaBancaria(final String numeroCuenta, double saldo, String titular, boolean activa) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.titular = titular;
        this.activa = activa;
        this.historialTransacciones = new ArrayList<>();
    }
    // Getters y Setters (menos para numeroCuenta que es final)
    public String getNumeroCuenta() { return numeroCuenta; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }

    public List<Transaccion> getHistorialTransacciones() { return historialTransacciones; }
    public void agregarTransaccion(Transaccion transaccion) {
        historialTransacciones.add(transaccion);
    }
}
