package ar.edu.unlar.programacion3.exception;

// validacion de acceso
public class PinInvalidoException extends Exception {
    public PinInvalidoException(String mensaje) {
        super(mensaje);
    }
    
}
