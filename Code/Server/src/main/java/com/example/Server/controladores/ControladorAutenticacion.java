package com.example.Server.controladores;
import com.example.Server.modelos.*;
import com.example.Server.servicios.ServicioAutenticacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAutenticacion {
    @Autowired
    private ServicioAutenticacion servicio;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario credenciales) {
        return ResponseEntity.ok(servicio.login(credenciales));
    }

    @PostMapping("/registro/estudiante")
    public ResponseEntity<Estudiante> registrarEstudiante(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(servicio.registrarEstudiante(estudiante));
    }

    @PostMapping("/registro/docente")
    public ResponseEntity<Docente> registrarDocente(@RequestBody Docente docente) {
        return ResponseEntity.ok(servicio.registrarDocente(docente));
    }
}
