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
        Calificacion creada = servicio.crear(calificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<Calificacion>> getCalificaciones() {
        List<Calificacion> calificaciones = servicio.getCalificaciones();
        return ResponseEntity.ok(calificaciones);
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<Calificacion>> getCalificacionesPorEstudiante(@PathVariable String estudianteCodigo) {
        List<Calificacion> calificaciones = servicio.getCalificacionesPorEstudiante(estudianteCodigo);
        return ResponseEntity.ok(calificaciones);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Calificacion calificacion) {
        servicio.eliminar(calificacion);
        return ResponseEntity.ok().build();
    }
}
