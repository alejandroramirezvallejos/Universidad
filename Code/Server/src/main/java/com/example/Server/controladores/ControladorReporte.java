package com.example.Server.controladores;

import com.example.Server.servicios.abstracciones.IServicioReporte;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorReporte {
    private final IServicioReporte servicio;

    @GetMapping
    public ResponseEntity<List<String>> listar() {
        List<String> reportes = servicio.getReportesDisponibles();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/estudiantes-por-carrera/{codigoCarrera}")
    public ResponseEntity<Map<String, Object>> reporteEstudiantesPorCarrera(@PathVariable String codigoCarrera, @RequestParam(defaultValue = "Sistema") String solicitante) {
        return ResponseEntity.ok(servicio.getReporteEstudiantesPorCarrera(codigoCarrera, solicitante));
    }

    @GetMapping("/inscripciones/{codigoGestion}")
    public ResponseEntity<Map<String, Object>> reporteInscripciones(@PathVariable String codigoGestion) {
        return ResponseEntity.ok(servicio.getReporteInscripcionesPorGestion(codigoGestion));
    }

    @GetMapping("/rendimiento/{codigoParalelo}")
    public ResponseEntity<Map<String, Object>> reporteRendimiento(@PathVariable String codigoParalelo, @RequestParam(defaultValue = "Sistema") String solicitante) {
        return ResponseEntity.ok(servicio.getReporteRendimientoPorParalelo(codigoParalelo, solicitante));
    }
}
