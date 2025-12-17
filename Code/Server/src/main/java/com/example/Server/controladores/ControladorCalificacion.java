package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.implementaciones.Calificacion;
import com.example.Server.servicios.abstracciones.IServicioCalificacion;
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
    private final IServicioCalificacion servicio;

    @PostMapping
    public ResponseEntity<ICalificacion> crear(@RequestBody Calificacion calificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(calificacion));
    }

    @GetMapping
    public ResponseEntity<List<ICalificacion>> getCalificaciones() {
        return ResponseEntity.ok(servicio.getCalificaciones());
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<ICalificacion>> getCalificacionesPorEstudiante(@PathVariable String estudianteCodigo) {
        return ResponseEntity.ok(servicio.getCalificacionesPorEstudiante(estudianteCodigo));
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Calificacion calificacion) {
        servicio.eliminar(calificacion);
        return ResponseEntity.ok().build();
    }
}
