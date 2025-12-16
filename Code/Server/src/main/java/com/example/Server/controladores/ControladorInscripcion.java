package com.example.Server.controladores;
import com.example.Server.casts.CastMatricula;
import com.example.Server.dtos.DtoInscripcion;
import com.example.Server.dtos.DtoInscripcionBatch;
import com.example.Server.dtos.DtoMatricula;
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
    private final CastMatricula convertidor;

    @PostMapping
    public ResponseEntity<DtoMatricula> inscribir(@RequestBody DtoInscripcion dto) {
        Matricula matricula = servicio.inscribir(dto.getEstudiante(), dto.getParaleloMateria());

        if (matricula == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        DtoMatricula dtoMatricula = convertidor.getDto(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMatricula);
    }

    @PutMapping("/aceptar")
    public ResponseEntity<Void> aceptar(@RequestBody DtoMatricula dto) {
        Matricula matricula = convertidor.getModelo(dto);
        servicio.aceptar(matricula);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DtoMatricula>> getMatriculas() {
        List<Matricula> matriculas = servicio.getMatriculas();
        List<DtoMatricula> dtos = new ArrayList<>();

        for (Matricula matricula : matriculas)
            dtos.add(convertidor.getDto(matricula));

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<DtoMatricula>> inscribirBatch(@RequestBody DtoInscripcionBatch dtoBatch) {
        List<DtoMatricula> matriculasCreadas = new ArrayList<>();

        for (DtoInscripcion inscripcion : dtoBatch.getInscripciones()) {
            Matricula matricula = servicio.inscribir(
                    inscripcion.getEstudiante(),
                    inscripcion.getParaleloMateria()
            );

            if (matricula != null)
                matriculasCreadas.add(convertidor.getDto(matricula));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(matriculasCreadas);
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<DtoMatricula>> obtenerPorEstudiante(@PathVariable String estudianteCodigo) {
        List<Matricula> matriculas = servicio.obtenerPorEstudiante(estudianteCodigo);
        List<DtoMatricula> dtos = new ArrayList<>();

        for (Matricula materia : matriculas)
            dtos.add(convertidor.getDto(materia));

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/paralelo/{paraleloCodigo}")
    public ResponseEntity<List<DtoMatricula>> obtenerPorParalelo(@PathVariable String paraleloCodigo) {
        List<Matricula> matriculas = servicio.obtenerPorParalelo(paraleloCodigo);
        List<DtoMatricula> dtos = new ArrayList<>();

        for (Matricula matricula : matriculas)
            dtos.add(convertidor.getDto(matricula));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{estudianteCodigo}/{paraleloCodigo}")
    public ResponseEntity<Void> cancelar(
            @PathVariable String estudianteCodigo,
            @PathVariable String paraleloCodigo) {
        servicio.cancelar(estudianteCodigo, paraleloCodigo);
        return ResponseEntity.ok().build();
    }
}
