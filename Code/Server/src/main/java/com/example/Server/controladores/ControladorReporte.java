package com.example.Server.controladores;
import com.example.Server.servicios.ServicioReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorReporte {
    @Autowired
    private ServicioReporte servicio;

    @GetMapping
    public ResponseEntity<List<String>> listarReportes() {
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
