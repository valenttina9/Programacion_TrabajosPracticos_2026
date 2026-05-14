package unlar.programacion3.PracticaParcial.model;

import lombok.Data;

@Data
public class Cliente {
    private String nombre;
    private String email;
    private String dni;
    private Sucursales sucursal;
}
