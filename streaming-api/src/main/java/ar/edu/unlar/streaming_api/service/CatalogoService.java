package ar.edu.unlar.streaming_api.service;

import ar.edu.unlar.streaming_api.Cancion;
import ar.edu.unlar.streaming_api.Genero;
import ar.edu.unlar.streaming_api.Artista;
import ar.edu.unlar.streaming_api.Album;
import ar.edu.unlar.streaming_api.service.strategy.RecomendacionStrategy;
import ar.edu.unlar.streaming_api.service.strategy.RecomendacionPorGenero;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service // Marca la clase como un servicio de Spring
public class CatalogoService {
    // Simulando una base de datos en memoria 
    private List<Cancion> canciones = new ArrayList<>();
    private RecomendacionStrategy recomendacionStrategy = new RecomendacionPorGenero();



    // Operaciones con Streams API
    
    // Top 10 más reproducidas
    public List<Cancion> getTop10MasReproducidas() {
        return canciones.stream()
                .sorted(Comparator.comparingInt((Cancion c) -> c.getReproducciones().get()).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    // Promedio de duración por género
    public Map<Genero, Double> getPromedioDuracionPorGenero() {
        return canciones.stream()
                .collect(Collectors.groupingBy(
                    Cancion::getGenero, 
                    Collectors.averagingInt(Cancion::getDuracionSegundos)
                ));
    }


    // Artista más popular (el que tiene la canción con más reproducciones)
    public String getNombreArtistaMasPopular() {
    return canciones.stream()
            // Buscamos la canción con más reproducciones
            .max(Comparator.comparingInt(c -> c.getReproducciones().get()))
            // Transformamos la canción encontrada en el nombre de su artista
            .map(cancion -> cancion.getArtista() != null ? cancion.getArtista().getNombre() : "Artista Desconocido")
            // Si la lista está vacía, devolvemos un mensaje por defecto
            .orElse("No hay canciones en el catálogo");
}
    
    @PostConstruct // Llama automáticamente al inicializar el bean (Spring)
    public void cargarDatosIniciales() {

        
        Cancion c1 = new Cancion();
        c1.setTitulo("Starboy");
        c1.setGenero(Genero.POP);
        c1.setDuracionSegundos(230);
        c1.setRating(4.8);
        c1.setFechaLanzamiento(LocalDate.of(2016, 9, 21));
        c1.getReproducciones().set(1500);
        c1.setArtista(new Artista("Ed Sheeran", "Inglaterra"));
        c1.setAlbum(new Album("Starboy", new Artista("Ed Sheeran", "Inglaterra")));
        
        Cancion c2 = new Cancion();
        c2.setTitulo("Bohemian Rhapsody");
        c2.setGenero(Genero.ROCK);
        c2.setDuracionSegundos(354);
        c2.setRating(5.0);
        c2.setFechaLanzamiento(LocalDate.of(1975, 10, 31));
        c2.getReproducciones().set(5000);
        c2.setArtista(new Artista("Queen", "Inglaterra"));
        c2.setAlbum(new Album("Bohemian Rhapsody", new Artista("Queen", "Inglaterra")));

        Cancion c3 = new Cancion();
        c3.setTitulo("Hotel California");
        c3.setGenero(Genero.ROCK);
        c3.setDuracionSegundos(310);
        c3.setRating(4.5);
        c3.setFechaLanzamiento(LocalDate.of(1977, 10, 1));
        c3.getReproducciones().set(3000);
        c3.setArtista(new Artista("Led Zeppelin", "Inglaterra"));
        c3.setAlbum(new Album("Hotel California", new Artista("Led Zeppelin", "Inglaterra")));
        
        canciones.add(c1);
        canciones.add(c2);
        canciones.add(c3);

        // crear 5 canciones diferentes de Taylor Swift
        Cancion c4 = new Cancion();
        c4.setTitulo("Love Story");
        c4.setGenero(Genero.POP);
        c4.setDuracionSegundos(240);
        c4.setRating(4.2);
        c4.setFechaLanzamiento(LocalDate.of(2014, 12, 1));
        c4.getReproducciones().set(2000);
        c4.setArtista(new Artista("Taylor Swift", "Inglaterra"));
        c4.setAlbum(new Album("Love Story", new Artista("Taylor Swift", "Inglaterra")));
        canciones.add(c4);

        Cancion c5 = new Cancion();
        c5.setTitulo("You Belong to Me");
        c5.setGenero(Genero.POP);
        c5.setDuracionSegundos(240);
        c5.setRating(4.2);
        c5.setFechaLanzamiento(LocalDate.of(2014, 12, 1));
        c5.getReproducciones().set(2000);
        c5.setArtista(new Artista("Taylor Swift", "Inglaterra"));
        c5.setAlbum(new Album("You Belong to Me", new Artista("Taylor Swift", "Inglaterra")));
        canciones.add(c5);
        
        System.out.println("Catálogo cargado con " + canciones.size() + " canciones");
}
    
    // Un método simple para listar todas
    public List<Cancion> getAll() {
    return canciones;
}

    // Método para incrementar reproducciones (POST)
    public void reproducir(String id) {
    canciones.stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .ifPresent(c -> c.getReproducciones().incrementAndGet());
}
    // Búsqueda filtrada, poniendo los parametros en postman
    public List<Cancion> buscar(String titulo, String artista) {
    return canciones.stream()
        .filter(c -> (titulo == null || c.getTitulo().toLowerCase().contains(titulo.toLowerCase())))
        .filter(c -> (artista == null || (c.getArtista() != null && c.getArtista().getNombre().toLowerCase().contains(artista.toLowerCase()))))
        .collect(Collectors.toList());

}
    // Obtener recomendaciones
    public List<Cancion> obtenerRecomendaciones(String cancionId){
        Cancion base = canciones.stream()
                .filter(c -> c.getId().equals(cancionId))
                .findFirst()
                .orElse(null);

        if (base == null) {
            return new ArrayList<Cancion>();
        }
        
        return recomendacionStrategy.recomendar(canciones, base);
    }


}

