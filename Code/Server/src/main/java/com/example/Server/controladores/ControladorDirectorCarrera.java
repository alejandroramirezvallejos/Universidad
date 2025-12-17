package com.example.Server.controladores;
import com.example.Server.modelos.DirectorCarrera;
import com.example.Server.servicios.ServicioDirectorCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/directores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorDirectorCarrera {
    private final ServicioDirectorCarrera servicio;

    @PostMapping
    public ResponseEntity<DirectorCarrera> crear(@RequestBody DirectorCarrera director) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(director));
    }

    @GetMapping
    public ResponseEntity<List<DirectorCarrera>> getDirectores() {
        return ResponseEntity.ok(servicio.getDirectores());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DirectorCarrera director) {
        servicio.eliminar(director);
        return ResponseEntity.ok().build();
    }
}
