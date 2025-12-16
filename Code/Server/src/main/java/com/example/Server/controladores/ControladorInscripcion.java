package com.example.Server.controladores;
import com.example.Server.modelos.Matricula;
import com.example.Server.servicios.ServicioInscripcion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorInscripcion {
    private final ServicioInscripcion servicio;

    @PostMapping
    public ResponseEntity<Matricula> inscribir(@RequestBody Matricula matricula) {
        Matricula creada = servicio.crear(matricula.getEstudiante(), matricula.getParaleloMateria());

        if (creada == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/aceptar")
    public ResponseEntity<Void> aceptar(@RequestBody Matricula matricula) {
        servicio.aceptar(matricula);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> getMatriculas() {
        List<Matricula> matriculas = servicio.getMatriculas();
        return ResponseEntity.ok(matriculas);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Matricula>> inscribirBatch(@RequestBody List<Matricula> inscripciones) {
        List<Matricula> matriculasCreadas = new ArrayList<>();

        for (Matricula inscripcion : inscripciones) {
            Matricula matricula = servicio.crear(
                    inscripcion.getEstudiante(),
                    inscripcion.getParaleloMateria()
            );

            if (matricula != null)
                matriculasCreadas.add(matricula);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(matriculasCreadas);
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<Matricula>> getInscripcionesPorEstudiante(@PathVariable String estudianteCodigo) {
        List<Matricula> matriculas = servicio.getMatriculasPorEstudiante(estudianteCodigo);
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/paralelo/{paraleloCodigo}")
    public ResponseEntity<List<Matricula>> getInscripcionesPorParalelo(@PathVariable String paraleloCodigo) {
        List<Matricula> matriculas = servicio.getMatriculasPorParalelo(paraleloCodigo);
        return ResponseEntity.ok(matriculas);
    }

    @DeleteMapping("/{estudianteCodigo}/{paraleloCodigo}")
    public ResponseEntity<Void> cancelar(
            @PathVariable String estudianteCodigo,
            @PathVariable String paraleloCodigo) {
        servicio.cancelar(estudianteCodigo, paraleloCodigo);
        return ResponseEntity.ok().build();
    }
}
