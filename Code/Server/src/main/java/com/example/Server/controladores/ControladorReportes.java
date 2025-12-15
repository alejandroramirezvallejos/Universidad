package com.example.Server.controladores;
import com.example.Server.dtos.*;
import com.example.Server.servicios.ServicioReportes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorReportes {
    @Autowired
    private ServicioReportes servicio;

    @GetMapping
    public ResponseEntity<List<String>> listarReportes() {
        List<String> reportes = servicio.listarReportesDisponibles();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/estudiantes-por-carrera/{codigoCarrera}")
    public ResponseEntity<DtoReporteEstudiantesPorCarrera> reporteEstudiantesPorCarrera(
            @PathVariable String codigoCarrera) {
        
        DtoReporteEstudiantesPorCarrera reporte = 
                servicio.reporteEstudiantesPorCarrera(codigoCarrera);

        if (reporte == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/inscripciones/{codigoGestion}")
    public ResponseEntity<DtoReporteInscripciones> reporteInscripciones(
            @PathVariable String codigoGestion) {
        
        DtoReporteInscripciones reporte = 
                servicio.reporteInscripcionesPorGestion(codigoGestion);

        if (reporte == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/rendimiento/{codigoParalelo}")
    public ResponseEntity<DtoReporteRendimiento> reporteRendimiento(
            @PathVariable String codigoParalelo) {
        
        DtoReporteRendimiento reporte = 
                servicio.reporteRendimientoPorParalelo(codigoParalelo);

        if (reporte == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(reporte);
    }
}
