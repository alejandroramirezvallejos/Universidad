package com.example.Server.controladores;
import com.example.Server.modelos.Gestion;
import com.example.Server.servicios.ServicioGestion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gestiones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorGestion {
    private final ServicioGestion servicio;

    @PostMapping
    public ResponseEntity<Gestion> crear(@RequestBody Gestion gestion) {
        try {
            Gestion creada = servicio.crear(gestion);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        }
        catch (Exception excepcion) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Gestion>> getGestiones() {
        List<Gestion> gestiones = servicio.getGestiones();
        return ResponseEntity.ok(gestiones);
    }

    @GetMapping("/actual")
    public ResponseEntity<Gestion> getGestionActual() {
        Optional<Gestion> gestionActual = servicio.getGestionActual();

        if (gestionActual.isPresent())
            return ResponseEntity.ok(gestionActual.get());

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Gestion> getGestionPorCodigo(@PathVariable String codigo) {
        Optional<Gestion> gestionActual = servicio.getGestionPorCodigo(codigo);

        if (gestionActual.isPresent())
            return ResponseEntity.ok(gestionActual.get());

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Gestion gestion) {
        try {
            servicio.eliminar(gestion);
            return ResponseEntity.ok().build();
        }
        catch (Exception excepcion) {
            return ResponseEntity.badRequest().build();
        }
    }
}
