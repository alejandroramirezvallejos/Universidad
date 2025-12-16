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
        Materia creada = servicio.crear(materia);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PostMapping("/agregar-carrera")
    public ResponseEntity<Materia> agregar(@RequestBody Materia materia) {
        Materia resultado = servicio.agregar(materia, materia.getCarrera());
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<Materia>> getMaterias() {
        List<Materia> materias = servicio.getMaterias();
        return ResponseEntity.ok(materias);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Materia materia) {
        servicio.eliminar(materia);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Materia> actualizar(@PathVariable String codigo, @RequestBody Materia materiaDto) {
        Materia materia = servicio.getMateriaPorCodigo(codigo);

        if (materia == null)
            return ResponseEntity.notFound().build();

        materia.setNombre(materiaDto.getNombre());
        materia.setSemestre(materiaDto.getSemestre());
        materia.setCreditos(materiaDto.getCreditos());

        Materia actualizada = servicio.crear(materia);
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/{codigo}/estado")
    public ResponseEntity<Materia> setEstado(@PathVariable String codigo) {
        Materia materia = servicio.getMateriaPorCodigo(codigo);

        if (materia == null)
            return ResponseEntity.notFound().build();

        materia.setActiva(!materia.isActiva());
        Materia actualizada = servicio.crear(materia);

        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Materia> getMateria(@PathVariable String codigo) {
        Materia materia = servicio.getMateriaPorCodigo(codigo);

        if (materia == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(materia);
    }
}
