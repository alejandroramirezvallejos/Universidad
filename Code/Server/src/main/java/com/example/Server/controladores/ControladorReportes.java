package com.example.Server.controladores;
import com.example.Server.servicios.ServicioReportes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorReportes {
    @Autowired
    private ServicioReportes servicio;

    @GetMapping
    public ResponseEntity<List<String>> listarReportes() {
        List<String> reportes = servicio.getReportesDisponibles();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/estudiantes-por-carrera/{codigoCarrera}")
    public ResponseEntity<Map<String, Object>> reporteEstudiantesPorCarrera(
            @PathVariable String codigoCarrera) {
        
        Map<String, Object> reporte =
                servicio.getReporteEstudiantesPorCarrera(codigoCarrera);

        if (reporte == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/inscripciones/{codigoGestion}")
    public ResponseEntity<Map<String, Object>> reporteInscripciones(
            @PathVariable String codigoGestion) {
        
        Map<String, Object> reporte =
                servicio.getReporteInscripcionesPorGestion(codigoGestion);

        if (reporte == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/rendimiento/{codigoParalelo}")
    public ResponseEntity<Map<String, Object>> reporteRendimiento(
            @PathVariable String codigoParalelo) {
        
        Map<String, Object> reporte =
                servicio.getReporteRendimientoPorParalelo(codigoParalelo);

        if (reporte == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(reporte);
    }
}
