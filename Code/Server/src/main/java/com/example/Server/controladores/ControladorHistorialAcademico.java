package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IHistorialAcademico;
import com.example.Server.modelos.implementaciones.Estudiante;
import com.example.Server.modelos.implementaciones.HistorialAcademico;
import com.example.Server.servicios.abstracciones.IServicioHistorialAcademico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorHistorialAcademico {
    private final IServicioHistorialAcademico servicio;

    @PostMapping
    public ResponseEntity<IHistorialAcademico> crear(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(servicio.crear(estudiante));
    }

    @GetMapping
    public ResponseEntity<List<IHistorialAcademico>> getHistoriales() {
        return ResponseEntity.ok(servicio.getHistoriales());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody HistorialAcademico historial) {
        servicio.eliminar(historial);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<IHistorialAcademico> getHistorial(@PathVariable String estudianteCodigo) {
        return ResponseEntity.ok(servicio.getHistorialPorEstudiante(estudianteCodigo));
    }

    @GetMapping("/estudiante/{estudianteCodigo}/promedio")
    public ResponseEntity<ICalificacion> getPromedio(@PathVariable String estudianteCodigo) {
        return ResponseEntity.ok(servicio.getPromedioEstudiante(estudianteCodigo));
    }
}
