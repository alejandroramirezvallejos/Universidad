package com.example.Server.controladores;
import com.example.Server.dtos.DtoDashboardEstudiante;
import com.example.Server.dtos.DtoDashboardDocente;
import com.example.Server.servicios.ServicioDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorDashboard {
    @Autowired
    private ServicioDashboard servicio;

    @GetMapping("/estudiante/{codigo}")
    public ResponseEntity<DtoDashboardEstudiante> dashboardEstudiante(@PathVariable String codigo) {
        DtoDashboardEstudiante dashboard = servicio.generarDashboardEstudiante(codigo);

        if (dashboard == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(dashboard);
    }

    @GetMapping("/docente/{codigo}")
    public ResponseEntity<DtoDashboardDocente> dashboardDocente(@PathVariable String codigo) {
        DtoDashboardDocente dashboard = servicio.generarDashboardDocente(codigo);

        if (dashboard == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(dashboard);
    }
}
