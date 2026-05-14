package unlar.programacion3.PracticaParcial.model;

import lombok.Data;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialMovimientos {
    private LocalDate fecha;
    private double monto;
    private String detalle;
    
}
