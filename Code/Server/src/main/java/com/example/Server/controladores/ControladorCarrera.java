package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.implementaciones.Carrera;
import com.example.Server.servicios.abstracciones.IServicioCarrera;
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
    private final IServicioCarrera servicio;

    @PostMapping
    public ResponseEntity<ICarrera> crear(@RequestBody Carrera carrera) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(carrera));
    }

    @GetMapping
    public ResponseEntity<List<ICarrera>> getCarreras() {
        return ResponseEntity.ok(servicio.getCarreras());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Carrera carrera) {
        servicio.eliminar(carrera);
        return ResponseEntity.ok().build();
    }
}
