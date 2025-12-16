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
        Evaluacion creada = servicio.crear(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/calificaciones")
    public ResponseEntity<Void> agregar(@RequestBody Calificacion calificacion) {
        servicio.agregar(calificacion);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Evaluacion>> getEvaluaciones() {
        List<Evaluacion> evaluaciones = servicio.getEvaluaciones();
        return ResponseEntity.ok(evaluaciones);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Evaluacion evaluacion) {
        servicio.eliminar(evaluacion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paralelo/{paraleloCodigo}")
    public ResponseEntity<List<Evaluacion>> getEvaluacionesPorParalelo(@PathVariable String paraleloCodigo) {
        List<Evaluacion> evaluaciones = servicio.obtenerPorParalelo(paraleloCodigo);
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<Calificacion>> getEvaluacionesPorEstudiante(@PathVariable String estudianteCodigo) {
        List<Calificacion> calificaciones = servicio.obtenerCalificacionesEstudiante(estudianteCodigo);
        return ResponseEntity.ok(calificaciones);
    }

    @PostMapping("/calificacion")
    public ResponseEntity<Calificacion> registrar(@RequestBody Calificacion calificacion) {
        servicio.agregar(calificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacion);
    }
}
