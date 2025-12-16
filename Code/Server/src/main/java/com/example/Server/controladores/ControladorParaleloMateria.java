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
        ParaleloMateria creado = servicio.crear(paralelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<ParaleloMateria>> getParalelos() {
        List<ParaleloMateria> paralelos = servicio.getParalelos();
        return ResponseEntity.ok(paralelos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody ParaleloMateria paralelo) {
        servicio.eliminar(paralelo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ParaleloMateria> actualizar(
            @PathVariable String codigo,
            @RequestBody ParaleloMateria paraleloDto) {
        ParaleloMateria paralelo = servicio.getParaleloPorCodigo(codigo);

        if (paralelo == null)
            return ResponseEntity.notFound().build();

        paralelo.setMateria(paraleloDto.getMateria());
        paralelo.setDocente(paraleloDto.getDocente());
        paralelo.setAula(paraleloDto.getAula());
        paralelo.setCupoMaximo(paraleloDto.getCupoMaximo());

        if (paraleloDto.getHorarios() != null)
            paralelo.setHorarios(paraleloDto.getHorarios());

        ParaleloMateria actualizado = servicio.crear(paralelo);

        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ParaleloMateria> getParaleloPorCodigo(@PathVariable String codigo) {
        ParaleloMateria paralelo = servicio.getParaleloPorCodigo(codigo);

        if (paralelo == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(paralelo);
    }

    @GetMapping("/docente/{docenteCodigo}")
    public ResponseEntity<List<ParaleloMateria>> getParaleloPorDocente(@PathVariable String docenteCodigo) {
        List<ParaleloMateria> paralelos = servicio.getParalelosPorDocente(docenteCodigo);
        return ResponseEntity.ok(paralelos);
    }

    @GetMapping("/materia/{materiaCodigo}")
    public ResponseEntity<List<ParaleloMateria>> getParaleloPorMateria(@PathVariable String materiaCodigo) {
        List<ParaleloMateria> paralelos = servicio.getParalelosPorMateria(materiaCodigo);
        return ResponseEntity.ok(paralelos);
    }
}

