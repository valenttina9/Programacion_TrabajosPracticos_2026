package ar.edu.unlar.programacion3.exception;

// saldo < monto solicitado 
public class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }

    
}
