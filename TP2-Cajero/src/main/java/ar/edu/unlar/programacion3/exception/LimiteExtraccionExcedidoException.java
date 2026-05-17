package ar.edu.unlar.programacion3.exception;

// monto > $10,000 por operación
public class LimiteExtraccionExcedidoException extends Exception {
    public LimiteExtraccionExcedidoException(String mensaje) {
        super(mensaje);
    }

    
}
