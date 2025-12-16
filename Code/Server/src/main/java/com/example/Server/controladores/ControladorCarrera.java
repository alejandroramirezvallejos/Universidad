package com.example.Server.controladores;
import com.example.Server.modelos.Carrera;
import com.example.Server.servicios.ServicioCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carreras")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorCarrera {
    private final ServicioCarrera servicio;

    @PostMapping
    public ResponseEntity<Carrera> crear(@RequestBody Carrera carrera) {
        Carrera creada = servicio.crear(carrera);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<Carrera>> getCarreras() {
        List<Carrera> carreras = servicio.getCarreras();
        return ResponseEntity.ok(carreras);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Carrera carrera) {
        servicio.eliminar(carrera);
        return ResponseEntity.ok().build();
    }
}
