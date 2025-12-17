package com.example.Server.controladores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.HistorialAcademico;
import com.example.Server.servicios.ServicioHistorialAcademico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorHistorialAcademico {
    private final ServicioHistorialAcademico servicio;

    @PostMapping
    public ResponseEntity<HistorialAcademico> crear(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(servicio.crear(estudiante));
    }

    @GetMapping
    public ResponseEntity<List<HistorialAcademico>> getHistoriales() {
        return ResponseEntity.ok(servicio.getHistoriales());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody HistorialAcademico historial) {
        servicio.eliminar(historial);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<HistorialAcademico> getHistorial(@PathVariable String estudianteCodigo) {
        HistorialAcademico historial = servicio.getHistorialPorEstudiante(estudianteCodigo);
        return historial == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(historial);
    }

    @GetMapping("/estudiante/{estudianteCodigo}/promedio")
    public ResponseEntity<Map<String, Object>> getPromedio(@PathVariable String estudianteCodigo) {
        Double promedio = servicio.calcularPromedioGeneral(estudianteCodigo);

        return ResponseEntity.ok(Map.of(
            "estudianteCodigo", estudianteCodigo,
            "promedio", promedio,
            "totalMaterias", 0
        ));
    }
}
