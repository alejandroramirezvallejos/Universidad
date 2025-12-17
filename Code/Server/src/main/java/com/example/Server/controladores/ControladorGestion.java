package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.IGestion;
import com.example.Server.modelos.implementaciones.Gestion;
import com.example.Server.servicios.abstracciones.IServicioGestion;
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
    private final IServicioGestion servicio;

    @PostMapping
    public ResponseEntity<IGestion> crear(@RequestBody Gestion gestion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(gestion));
    }

    @GetMapping
    public ResponseEntity<List<IGestion>> getGestiones() {
        return ResponseEntity.ok(servicio.getGestiones());
    }

    @GetMapping("/actual")
    public ResponseEntity<IGestion> getGestionActual() {
        return ResponseEntity.ok(servicio.getGestion());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<IGestion> getGestionPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.getGestionPorCodigo(codigo));
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Gestion gestion) {
        servicio.eliminar(gestion);
        return ResponseEntity.ok().build();
    }
}
