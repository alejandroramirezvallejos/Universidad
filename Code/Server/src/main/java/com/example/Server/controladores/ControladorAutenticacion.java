package com.example.Server.controladores;
import com.example.Server.modelos.*;
import com.example.Server.servicios.ServicioAutenticacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAutenticacion {
    @Autowired
    private ServicioAutenticacion servicio;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario credenciales) {
        try {
            Usuario usuario = servicio.login(credenciales);
            return ResponseEntity.ok(usuario);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }

    @PostMapping("/registro/estudiante")
    public ResponseEntity<?> registrarEstudiante(@RequestBody Estudiante estudiante) {
        try {
            Estudiante creado = servicio.registrarEstudiante(estudiante);
            return ResponseEntity.ok(creado);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }

    @PostMapping("/registro/docente")
    public ResponseEntity<?> registrarDocente(@RequestBody Docente docente) {
        try {
            Docente creado = servicio.registrarDocente(docente);
            return ResponseEntity.ok(creado);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }
}
