package com.example.Server.controladores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.servicios.ServicioEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorEstudiante {
    private final ServicioEstudiante servicio;

    @PostMapping
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) {
        Estudiante creado = servicio.crear(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> getEstudiantes() {
        List<Estudiante> estudiantes = servicio.getEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Estudiante estudiante) {
        servicio.eliminar(estudiante);
        return ResponseEntity.ok().build();
    }
}
