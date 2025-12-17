package com.example.Server.controladores;
import com.example.Server.modelos.Materia;
import com.example.Server.servicios.ServicioMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorMateria {
    private final ServicioMateria servicio;

    @PostMapping
    public ResponseEntity<Materia> crear(@RequestBody Materia materia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(materia));
    }

    @PostMapping("/agregar-carrera")
    public ResponseEntity<Materia> agregar(@RequestBody Materia materia) {
        return ResponseEntity.ok(servicio.agregar(materia, materia.getCarrera()));
    }

    @GetMapping
    public ResponseEntity<List<Materia>> getMaterias() {
        return ResponseEntity.ok(servicio.getMaterias());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Materia materia) {
        servicio.eliminar(materia);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Materia> actualizar(@PathVariable String codigo, @RequestBody Materia materiaDto) {
        return ResponseEntity.ok(servicio.actualizar(codigo, materiaDto));
    }

    @PatchMapping("/{codigo}/estado")
    public ResponseEntity<Materia> setEstado(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.cambiarEstado(codigo));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Materia> getMateria(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.getMateriaPorCodigo(codigo));
    }
}
