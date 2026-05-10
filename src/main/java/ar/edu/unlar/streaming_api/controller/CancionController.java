package ar.edu.unlar.streaming_api.controller;

import ar.edu.unlar.streaming_api.Cancion;
import ar.edu.unlar.streaming_api.service.CatalogoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    private final CatalogoService catalogoService;

    public CancionController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping("/top10")
    public List<Cancion> getTop10() {
        return catalogoService.getTop10MasReproducidas();
    }

     @GetMapping
    public List<Cancion> listarTodas() {
        return catalogoService.getAll();
    }

    @PostMapping("/{id}/reproducir")
    public String reproducir(@PathVariable String id) {
        catalogoService.reproducir(id);
        return "Reproducción incrementada";
}
    // Buscar canciones
    @GetMapping("/buscar")
    public List<Cancion> buscar(@RequestParam(required = false) String titulo, 
                           @RequestParam(required = false) String artista) {
    return catalogoService.buscar(titulo, artista);
}
    // Obtener artista más popular
    @GetMapping("/ArtistaMasPopular")
    public String getArtistaMasPopular() {
        return catalogoService.getNombreArtistaMasPopular();
    }
    // Obtener recomendaciones
    @GetMapping("/{id}/recomendaciones")
    public List<Cancion> obtenerRecomendaciones(@PathVariable String id) {
        return catalogoService.obtenerRecomendaciones(id);
    }
}