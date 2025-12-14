package com.example.Server.controladores;

import com.example.Server.dtos.DtoDashboardEstudiante;
import com.example.Server.dtos.DtoDashboardDocente;
import com.example.Server.servicios.ServicioDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para dashboard y estadísticas
 * Endpoints: dashboard por rol
 */
@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorDashboard {

    @Autowired
    private ServicioDashboard servicioDashboard;

    /**
     * GET /api/dashboard/estudiante/{codigo}
     * Obtiene estadísticas y métricas para un estudiante
     */
    @GetMapping("/estudiante/{codigo}")
    public ResponseEntity<DtoDashboardEstudiante> dashboardEstudiante(@PathVariable String codigo) {
        DtoDashboardEstudiante dashboard = servicioDashboard.generarDashboardEstudiante(codigo);
        
        if (dashboard == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(dashboard);
    }

    /**
     * GET /api/dashboard/docente/{codigo}
     * Obtiene estadísticas y métricas para un docente
     */
    @GetMapping("/docente/{codigo}")
    public ResponseEntity<DtoDashboardDocente> dashboardDocente(@PathVariable String codigo) {
        DtoDashboardDocente dashboard = servicioDashboard.generarDashboardDocente(codigo);
        
        if (dashboard == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(dashboard);
    }
}
