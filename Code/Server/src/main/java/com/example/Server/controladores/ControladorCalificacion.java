package com.example.Server.controladores;
import com.example.Server.modelos.Calificacion;
import com.example.Server.servicios.ServicioCalificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorCalificacion {
    private final ServicioCalificacion servicio;

    @PostMapping
    public ResponseEntity<Calificacion> crear(@RequestBody Calificacion calificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(calificacion));
    }

    @GetMapping
    public ResponseEntity<List<Calificacion>> getCalificaciones() {
        return ResponseEntity.ok(servicio.getCalificaciones());
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<Calificacion>> getCalificacionesPorEstudiante(@PathVariable String estudianteCodigo) {
        return ResponseEntity.ok(servicio.getCalificacionesPorEstudiante(estudianteCodigo));
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Calificacion calificacion) {
        servicio.eliminar(calificacion);
        return ResponseEntity.ok().build();
    }
}
