package com.example.Server.controladores;
import com.example.Server.modelos.Gestion;
import com.example.Server.servicios.ServicioGestion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gestiones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorGestion {
    private final ServicioGestion servicio;

    @PostMapping
    public ResponseEntity<Gestion> crear(@RequestBody Gestion gestion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(gestion));
    }

    @GetMapping
    public ResponseEntity<List<Gestion>> getGestiones() {
        return ResponseEntity.ok(servicio.getGestiones());
    }

    @GetMapping("/actual")
    public ResponseEntity<Gestion> getGestionActual() {
        return ResponseEntity.ok(servicio.getGestionActual());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Gestion> getGestionPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.getGestionPorCodigo(codigo));
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Gestion gestion) {
        servicio.eliminar(gestion);
        return ResponseEntity.ok().build();
    }
}
