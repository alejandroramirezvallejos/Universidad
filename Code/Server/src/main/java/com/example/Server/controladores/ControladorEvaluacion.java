package com.example.Server.controladores;
import com.example.Server.modelos.Calificacion;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.servicios.ServicioEvaluacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorEvaluacion {
    private final ServicioEvaluacion servicio;

    @PostMapping
    public ResponseEntity<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(evaluacion.getParaleloMateria(), evaluacion.getNombre(), evaluacion.getPorcentaje()));
    }

    @PutMapping("/calificaciones")
    public ResponseEntity<Void> agregar(@RequestBody Calificacion calificacion) {
        servicio.agregar(calificacion.getEvaluacion(), calificacion);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Evaluacion>> getEvaluaciones() {
        return ResponseEntity.ok(servicio.getEvaluaciones());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Evaluacion evaluacion) {
        servicio.eliminar(evaluacion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paralelo/{paraleloCodigo}")
    public ResponseEntity<List<Evaluacion>> getEvaluacionesPorParalelo(@PathVariable String paraleloCodigo) {
        return ResponseEntity.ok(servicio.getEvaluacionesPorParalelo(paraleloCodigo));
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<Calificacion>> getEvaluacionesPorEstudiante(@PathVariable String estudianteCodigo) {
        return ResponseEntity.ok(servicio.getCalificacionesEstudiante(estudianteCodigo));
    }

    @PostMapping("/calificacion")
    public ResponseEntity<Calificacion> registrar(@RequestBody Calificacion calificacion) {
        servicio.agregar(calificacion.getEvaluacion(), calificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacion);
    }
}
