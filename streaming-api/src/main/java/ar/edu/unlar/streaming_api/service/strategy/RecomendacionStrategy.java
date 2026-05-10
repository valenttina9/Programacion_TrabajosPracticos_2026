package ar.edu.unlar.streaming_api.service.strategy;

import ar.edu.unlar.streaming_api.Cancion;
import java.util.List;

// Esto es una interface, no una clase abstracta
public interface RecomendacionStrategy {
    List<Cancion> recomendar(List<Cancion> catalogo, Cancion base);

}
