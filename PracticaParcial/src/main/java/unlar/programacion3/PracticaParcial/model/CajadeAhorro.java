package unlar.programacion3.PracticaParcial.model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CajadeAhorro extends Cuenta {
    private double limiteDescubierto;
}
