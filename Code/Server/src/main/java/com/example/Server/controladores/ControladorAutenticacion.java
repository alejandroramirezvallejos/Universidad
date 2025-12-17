package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.implementaciones.Docente;
import com.example.Server.modelos.implementaciones.Estudiante;
import com.example.Server.servicios.abstracciones.IServicioAutenticacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAutenticacion {
    private final IServicioAutenticacion servicio;

    @PostMapping("/login")
    public ResponseEntity<AUsuario> login(@RequestBody Estudiante credenciales) {
        return ResponseEntity.ok(servicio.login(credenciales));
    }

    @PostMapping("/registro/estudiante")
    public ResponseEntity<IEstudiante> registrarEstudiante(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(servicio.registrarEstudiante(estudiante));
    }

    @PostMapping("/registro/docente")
    public ResponseEntity<IDocente> registrarDocente(@RequestBody Docente docente) {
        return ResponseEntity.ok(servicio.registrarDocente(docente));
    }
}
