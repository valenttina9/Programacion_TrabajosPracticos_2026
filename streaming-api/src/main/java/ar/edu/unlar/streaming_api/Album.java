package ar.edu.unlar.streaming_api;

import lombok.Data;
import lombok.NoArgsConstructor; // Constructor sin argumentos
import lombok.AllArgsConstructor; // Constructor con argumentos

@NoArgsConstructor
@AllArgsConstructor 
@Data
public class Album {
    private String nombre;
    private Artista artista;
    
}
