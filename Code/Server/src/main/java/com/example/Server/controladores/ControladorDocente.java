package com.example.Server.controladores;

import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.modelos.implementaciones.Docente;
import com.example.Server.servicios.abstracciones.IServicioDocente;
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
    private final IServicioDocente servicio;

    @PostMapping
    public ResponseEntity<IDocente> crear(@RequestBody Docente docente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(docente));
    }

    @GetMapping
    public ResponseEntity<List<IDocente>> getDocentes() {
        return ResponseEntity.ok(servicio.getDocentes());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Docente docente) {
        servicio.eliminar(docente);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activos")
    public ResponseEntity<List<IDocente>> getDocentesActivos() {
        return ResponseEntity.ok(servicio.getDocentesActivos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<IDocente> getDocentePorCodigo(@PathVariable String codigo) {
        IDocente docente = servicio.buscarPorCodigo(codigo);
        return docente == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(docente);
    }
}
