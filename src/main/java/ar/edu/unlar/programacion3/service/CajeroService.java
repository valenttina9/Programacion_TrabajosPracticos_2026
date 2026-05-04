package ar.edu.unlar.programacion3.service;

import java.time.LocalDateTime;
import ar.edu.unlar.programacion3.exception.*;
import ar.edu.unlar.programacion3.model.*;
import java.util.List;

public class CajeroService {
    
    // validar cuenta si puede operar Cuenta inactiva exception
    private void validarCuenta(CuentaBancaria cuenta) throws CuentaInactivaException {
        if (!cuenta.isActiva()) { // si cuenta es false, cuenta desactivada
            throw new CuentaInactivaException("Cuenta desactivada");
        }
    }

    // Deposito
    public void depositar(CuentaBancaria cuenta, double monto) throws CuentaInactivaException {
        validarCuenta(cuenta); // usamos la funcion anterior
        cuenta.setSaldo(cuenta.getSaldo() + monto); // actualizamos saldo
        System.out.println("Depósito exitoso");
        
        // registro Transaccion transaccion
        Transaccion transaccion = new Transaccion(TransaccionTipo.DEPOSITO, monto, LocalDateTime.now(),"Deposito");
        cuenta.agregarTransaccion(transaccion);
    }
    // Extraccion
    public void extraer(CuentaBancaria cuenta, double monto) throws CuentaInactivaException,SaldoInsuficienteException,LimiteExtraccionExcedidoException {
        validarCuenta(cuenta); // usamos la funcion anterior

        // saldo insuficiente exception
        if (monto > cuenta.getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        // limite extraccion excedido exception
        if (monto > 10000) {
            throw new LimiteExtraccionExcedidoException("Monto excedido");
        }

        cuenta.setSaldo(cuenta.getSaldo() - monto); // actualizamos saldo
        System.out.println("Extracción exitosa");
        
        // registro Transaccion transaccion
        Transaccion transaccion = new Transaccion(TransaccionTipo.EXTRACCION, monto, LocalDateTime.now(),"Extracción");
        cuenta.agregarTransaccion(transaccion);
    }
    // transferencia
    public void transferir(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, double monto) throws CuentaInactivaException,SaldoInsuficienteException,LimiteExtraccionExcedidoException {
        validarCuenta(cuentaOrigen); // usamos la funcion anterior
        validarCuenta(cuentaDestino); // usamos la funcion anterior

        if (cuentaOrigen.getSaldo() < monto) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        // hacemos transferencia
        extraer(cuentaOrigen, monto); // usamos la funcion anterior
        depositar(cuentaDestino, monto); // usamos la funcion anterior
        System.out.println("Transferencia exitosa");

        // registro transaccion
        Transaccion transaccion = new Transaccion(TransaccionTipo.TRANSFERENCIA, monto, LocalDateTime.now(),"Transferencia");
        cuentaOrigen.agregarTransaccion(transaccion);
        cuentaDestino.agregarTransaccion(transaccion);
    }
    // consulta de historial
    public List<Transaccion> consultarHistorial(CuentaBancaria cuenta) {
        List<Transaccion> total = cuenta.getHistorialTransacciones();
        return total;
    }
}
