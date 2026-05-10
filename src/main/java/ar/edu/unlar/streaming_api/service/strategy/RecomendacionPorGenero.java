package ar.edu.unlar.streaming_api.service.strategy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import ar.edu.unlar.streaming_api.Cancion;

public class RecomendacionPorGenero implements RecomendacionStrategy {
    @Override
    public List<Cancion> recomendar(List<Cancion> catalogo, Cancion base) {
        return catalogo.stream()
            .filter(c -> c.getGenero().equals(base.getGenero()))
            .sorted(Comparator.comparingDouble(Cancion::getRating).reversed())
            .collect(Collectors.toList());
    }
}
