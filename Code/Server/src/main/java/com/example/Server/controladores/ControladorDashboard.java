package com.example.Server.controladores;
import com.example.Server.servicios.ServicioDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorDashboard {
    @Autowired
    private ServicioDashboard servicio;

    @GetMapping("/estudiante/{codigo}")
    public ResponseEntity<Map<String, Object>> getDashboardEstudiante(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.generarDashboardEstudiante(codigo));
    }

    @GetMapping("/docente/{codigo}")
    public ResponseEntity<Map<String, Object>> getDashboardDocente(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.generarDashboardDocente(codigo));
    }
}
