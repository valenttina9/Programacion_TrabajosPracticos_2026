package unlar.programacion3.PracticaParcial.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cuenta {
    private Long id; // Add id field
    private String cbu;
    private double saldo;
    private List<HistorialMovimientos> historialMovimientos = new ArrayList<>();
    private Cliente cliente;
}
