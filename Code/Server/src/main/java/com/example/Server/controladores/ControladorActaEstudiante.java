package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.implementaciones.ActaEstudiante;
import com.example.Server.servicios.abstracciones.IServicioActaEstudiante;
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
    private final IServicioActaEstudiante servicio;

    @PostMapping
    public ResponseEntity<IActaEstudiante> crear(@RequestBody ActaEstudiante acta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(acta.getEstudiante(), acta.getParaleloMateria()));
    }

    @GetMapping
    public ResponseEntity<List<IActaEstudiante>> getActas() {
        return ResponseEntity.ok(servicio.getActas());
    }

    @GetMapping("/aprobadas")
    public ResponseEntity<List<IActaEstudiante>> getActasAprobadas() {
        return ResponseEntity.ok(servicio.getActasAprobadas());
    }

    @GetMapping("/reprobadas")
    public ResponseEntity<List<IActaEstudiante>> getActasReprobadas() {
        return ResponseEntity.ok(servicio.getActasReprobadas());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody ActaEstudiante acta) {
        servicio.eliminar(acta);
        return ResponseEntity.ok().build();
    }
}
