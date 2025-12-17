package com.example.Server.controladores;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.servicios.ServicioParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/paralelos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorParaleloMateria {
    private final ServicioParaleloMateria servicio;

    @PostMapping
    public ResponseEntity<ParaleloMateria> crear(@RequestBody ParaleloMateria paralelo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(paralelo));
    }

    @GetMapping
    public ResponseEntity<List<ParaleloMateria>> getParalelos() {
        return ResponseEntity.ok(servicio.getParalelos());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody ParaleloMateria paralelo) {
        servicio.eliminar(paralelo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ParaleloMateria> actualizar(@PathVariable String codigo,  @RequestBody ParaleloMateria paraleloDto) {
        return ResponseEntity.ok(servicio.actualizar(codigo, paraleloDto));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ParaleloMateria> getParaleloPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.getParaleloPorCodigo(codigo));
    }

    @GetMapping("/docente/{docenteCodigo}")
    public ResponseEntity<List<ParaleloMateria>> getParaleloPorDocente(@PathVariable String docenteCodigo) {
        return ResponseEntity.ok(servicio.getParalelosPorDocente(docenteCodigo));
    }

    @GetMapping("/materia/{materiaCodigo}")
    public ResponseEntity<List<ParaleloMateria>> getParaleloPorMateria(@PathVariable String materiaCodigo) {
        return ResponseEntity.ok(servicio.getParalelosPorMateria(materiaCodigo));
    }
}

