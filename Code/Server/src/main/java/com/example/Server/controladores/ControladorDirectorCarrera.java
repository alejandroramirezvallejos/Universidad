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
        DirectorCarrera creado = servicio.crear(director);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<DirectorCarrera>> getDirectores() {
        List<DirectorCarrera> directores = servicio.getDirectores();
        return ResponseEntity.ok(directores);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DirectorCarrera director) {
        servicio.eliminar(director);
        return ResponseEntity.ok().build();
    }
}
