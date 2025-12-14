package com.example.Server.controladores;

import com.example.Server.dtos.*;
import com.example.Server.servicios.ServicioReportes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para reportes y análisis
 * Endpoints: reportes académicos y estadísticas
 */
@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorReportes {

    @Autowired
    private ServicioReportes servicioReportes;

    /**
     * GET /api/reportes
     * Lista todos los reportes disponibles
     */
    @GetMapping
    public ResponseEntity<List<String>> listarReportes() {
        List<String> reportes = servicioReportes.listarReportesDisponibles();
        return ResponseEntity.ok(reportes);
    }

    /**
     * GET /api/reportes/estudiantes-por-carrera/{codigoCarrera}
     * Reporte de estudiantes agrupados por carrera
     */
    @GetMapping("/estudiantes-por-carrera/{codigoCarrera}")
    public ResponseEntity<DtoReporteEstudiantesPorCarrera> reporteEstudiantesPorCarrera(
            @PathVariable String codigoCarrera) {
        
        DtoReporteEstudiantesPorCarrera reporte = 
                servicioReportes.reporteEstudiantesPorCarrera(codigoCarrera);
        
        if (reporte == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(reporte);
    }

    /**
     * GET /api/reportes/inscripciones/{codigoGestion}
     * Reporte de inscripciones por gestión académica
     */
    @GetMapping("/inscripciones/{codigoGestion}")
    public ResponseEntity<DtoReporteInscripciones> reporteInscripciones(
            @PathVariable String codigoGestion) {
        
        DtoReporteInscripciones reporte = 
                servicioReportes.reporteInscripcionesPorGestion(codigoGestion);
        
        if (reporte == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(reporte);
    }

    /**
     * GET /api/reportes/rendimiento/{codigoParalelo}
     * Reporte de rendimiento académico por paralelo
     */
    @GetMapping("/rendimiento/{codigoParalelo}")
    public ResponseEntity<DtoReporteRendimiento> reporteRendimiento(
            @PathVariable String codigoParalelo) {
        
        DtoReporteRendimiento reporte = 
                servicioReportes.reporteRendimientoPorParalelo(codigoParalelo);
        
        if (reporte == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(reporte);
    }
}
