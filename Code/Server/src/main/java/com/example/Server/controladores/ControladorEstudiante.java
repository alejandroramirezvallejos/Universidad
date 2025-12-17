package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.implementaciones.Estudiante;
import com.example.Server.servicios.abstracciones.IServicioEstudiante;
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
    private final IServicioEstudiante servicio;

    @PostMapping
    public ResponseEntity<IEstudiante> crear(@RequestBody Estudiante estudiante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(estudiante));
    }

    @GetMapping
    public ResponseEntity<List<IEstudiante>> getEstudiantes() {
        return ResponseEntity.ok(servicio.getEstudiantes());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Estudiante estudiante) {
        servicio.eliminar(estudiante);
        return ResponseEntity.ok().build();
    }
}
