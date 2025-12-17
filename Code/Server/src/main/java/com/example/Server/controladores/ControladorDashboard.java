package com.example.Server.controladores;

import com.example.Server.servicios.abstracciones.IServicioDashboard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorDashboard {
    private final IServicioDashboard servicio;

    @GetMapping("/estudiante/{codigo}")
    public ResponseEntity<Map<String, Object>> getDashboardEstudiante(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.generarDashboardEstudiante(codigo));
    }

    @GetMapping("/docente/{codigo}")
    public ResponseEntity<Map<String, Object>> getDashboardDocente(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.generarDashboardDocente(codigo));
    }
}
