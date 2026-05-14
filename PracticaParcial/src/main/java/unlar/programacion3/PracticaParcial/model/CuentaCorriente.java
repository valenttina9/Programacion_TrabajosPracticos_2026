package unlar.programacion3.PracticaParcial.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CuentaCorriente extends Cuenta {
    private double tasaInteres;
}
