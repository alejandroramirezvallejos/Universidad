package com.example.Server.controladores;
import com.example.Server.modelos.Docente;
import com.example.Server.servicios.ServicioDocente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/docentes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorDocente {
    private final ServicioDocente servicio;

    @PostMapping
    public ResponseEntity<Docente> crear(@RequestBody Docente docente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(docente));
    }

    @GetMapping
    public ResponseEntity<List<Docente>> getDocentes() {
        return ResponseEntity.ok(servicio.getDocentes());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Docente docente) {
        servicio.eliminar(docente);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Docente>> getDocentesActivos() {
        return ResponseEntity.ok(servicio.getDocentesActivos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Docente> getDocentePorCodigo(@PathVariable String codigo) {
        Docente docente = servicio.buscarPorCodigo(codigo);
        return docente == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(docente);
    }
}
