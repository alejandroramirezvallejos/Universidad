package com.example.Server.controladores;

import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.implementaciones.Calificacion;
import com.example.Server.modelos.implementaciones.Evaluacion;
import com.example.Server.servicios.abstracciones.IServicioEvaluacion;
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
    private final IServicioEvaluacion servicio;

    @PostMapping
    public ResponseEntity<IEvaluacion> crear(@RequestBody Evaluacion evaluacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(evaluacion.getParaleloMateria(), evaluacion.getNombre(), evaluacion.getPorcentaje()));
    }

    @PutMapping("/calificaciones")
    public ResponseEntity<Void> agregar(@RequestBody Calificacion calificacion) {
        servicio.agregar(calificacion.getEvaluacion(), calificacion);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<IEvaluacion>> getEvaluaciones() {
        return ResponseEntity.ok(servicio.getEvaluaciones());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Evaluacion evaluacion) {
        servicio.eliminar(evaluacion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paralelo/{paraleloCodigo}")
    public ResponseEntity<List<IEvaluacion>> getEvaluacionesPorParalelo(@PathVariable String paraleloCodigo) {
        return ResponseEntity.ok(servicio.getEvaluacionesPorParalelo(paraleloCodigo));
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<ICalificacion>> getEvaluacionesPorEstudiante(@PathVariable String estudianteCodigo) {
        return ResponseEntity.ok(servicio.getCalificacionesEstudiante(estudianteCodigo));
    }

    @PostMapping("/calificacion")
    public ResponseEntity<ICalificacion> registrar(@RequestBody Calificacion calificacion) {
        servicio.agregar(calificacion.getEvaluacion(), calificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacion);
    }
}
