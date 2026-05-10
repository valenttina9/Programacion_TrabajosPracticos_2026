package ar.edu.unlar.streaming_api;

import lombok.Data;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.LocalDate;

@Data // Genera getters y setters gracias a lombok
public class Cancion {
    private String id = UUID.randomUUID().toString();
    private String titulo;
    private Genero genero; // El ENUM que pide el profe
    private int duracionSegundos;
    private AtomicInteger reproducciones = new AtomicInteger(0);
    private double rating;
    private LocalDate fechaLanzamiento;
    
    // Relaciones
    private Album album;
    private Artista artista;
}
