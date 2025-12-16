package com.example.Server.controladores;
import com.example.Server.modelos.ActaEstudiante;
import com.example.Server.servicios.ServicioActaEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/actas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorActaEstudiante {
    private final ServicioActaEstudiante servicio;

    @PostMapping
    public ResponseEntity<ActaEstudiante> crear(@RequestBody ActaEstudiante acta) {
        ActaEstudiante creada = servicio.crear(acta.getEstudiante(), acta.getParaleloMateria());
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<ActaEstudiante>> getActas() {
        List<ActaEstudiante> actas = servicio.getActas();
        return ResponseEntity.ok(actas);
    }

    @GetMapping("/aprobadas")
    public ResponseEntity<List<ActaEstudiante>> getActasAprobadas() {
        List<ActaEstudiante> actas = servicio.getActasAprobadas();
        return ResponseEntity.ok(actas);
    }

    @GetMapping("/reprobadas")
    public ResponseEntity<List<ActaEstudiante>> getActasReprobadas() {
        List<ActaEstudiante> actas = servicio.getActasReprobadas();
        return ResponseEntity.ok(actas);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody ActaEstudiante acta) {
        servicio.eliminar(acta);
        return ResponseEntity.ok().build();
    }
}
