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
        Docente creado = servicio.crear(docente);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Docente>> getDocentes() {
        List<Docente> docentes = servicio.getDocentes();
        return ResponseEntity.ok(docentes);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Docente docente) {
        servicio.eliminar(docente);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Docente>> getDocentesActivos() {
        List<Docente> docentes = servicio.getDocentesActivos();
        return ResponseEntity.ok(docentes);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Docente> getDocentes(@PathVariable String codigo) {
        Docente docente = servicio.buscarPorCodigo(codigo);

        if (docente == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(docente);
    }
}
