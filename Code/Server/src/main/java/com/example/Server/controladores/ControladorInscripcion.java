package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.IMatricula;
import com.example.Server.modelos.implementaciones.Matricula;
import com.example.Server.servicios.abstracciones.IServicioInscripcion;
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
    private final IServicioInscripcion servicio;

    @PostMapping
    public ResponseEntity<IMatricula> inscribir(@RequestBody Matricula matricula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(matricula.getEstudiante(), matricula.getParaleloMateria()));
    }

    @PutMapping("/aceptar")
    public ResponseEntity<Void> aceptar(@RequestBody Matricula matricula) {
        servicio.aceptar(matricula);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<IMatricula>> getMatriculas() {
        return ResponseEntity.ok(servicio.getMatriculas());
    }

    @PostMapping("/batch")
    public ResponseEntity<List<IMatricula>> inscribirBatch(@RequestBody List<Matricula> inscripciones) {
        List<IMatricula> lista = new ArrayList<>(inscripciones);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.inscribirBatch(lista));
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<IMatricula>> getInscripcionesPorEstudiante(@PathVariable String estudianteCodigo) {
        return ResponseEntity.ok(servicio.getMatriculasPorEstudiante(estudianteCodigo));
    }

    @GetMapping("/paralelo/{paraleloCodigo}")
    public ResponseEntity<List<IMatricula>> getInscripcionesPorParalelo(@PathVariable String paraleloCodigo) {
        return ResponseEntity.ok(servicio.getMatriculasPorParalelo(paraleloCodigo));
    }

    @DeleteMapping("/{estudianteCodigo}/{paraleloCodigo}")
    public ResponseEntity<Void> cancelar(@PathVariable String estudianteCodigo, @PathVariable String paraleloCodigo) {
        servicio.cancelar(estudianteCodigo, paraleloCodigo);
        return ResponseEntity.ok().build();
    }
}
