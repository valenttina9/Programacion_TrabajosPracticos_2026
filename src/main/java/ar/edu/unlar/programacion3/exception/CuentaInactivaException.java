package ar.edu.unlar.programacion3.exception;

// cuenta desactivada
public class CuentaInactivaException extends Exception {
    public CuentaInactivaException(String mensaje) {
        super(mensaje);
    }
    
}
