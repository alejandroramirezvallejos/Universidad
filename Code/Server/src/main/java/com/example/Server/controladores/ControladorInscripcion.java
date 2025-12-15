package com.example.Server.controladores;
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

    @PostMapping
    public ResponseEntity<DtoMatricula> inscribir(@RequestBody DtoInscripcion dto) {
        Matricula matricula = servicio.inscribir(dto.getEstudiante(), dto.getParaleloMateria());

        if (matricula == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        DtoMatricula dtoMatricula = castDto(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMatricula);
    }

    @PutMapping("/aceptar")
    public ResponseEntity<Void> aceptar(@RequestBody DtoMatricula dto) {
        Matricula matricula = castModelo(dto);
        servicio.aceptar(matricula);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DtoMatricula>> getMatriculas() {
        List<Matricula> matriculas = servicio.getMatriculas();
        List<DtoMatricula> dtos = new ArrayList<>();

        for (Matricula matricula : matriculas)
            dtos.add(castDto(matricula));

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
                matriculasCreadas.add(castDto(matricula));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(matriculasCreadas);
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<DtoMatricula>> obtenerPorEstudiante(@PathVariable String estudianteCodigo) {
        List<Matricula> matriculas = servicio.obtenerPorEstudiante(estudianteCodigo);
        List<DtoMatricula> dtos = new ArrayList<>();

        for (Matricula materia : matriculas)
            dtos.add(castDto(materia));

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/paralelo/{paraleloCodigo}")
    public ResponseEntity<List<DtoMatricula>> obtenerPorParalelo(@PathVariable String paraleloCodigo) {
        List<Matricula> matriculas = servicio.obtenerPorParalelo(paraleloCodigo);
        List<DtoMatricula> dtos = new ArrayList<>();

        for (Matricula matricula : matriculas)
            dtos.add(castDto(matricula));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{estudianteCodigo}/{paraleloCodigo}")
    public ResponseEntity<Void> cancelar(
            @PathVariable String estudianteCodigo,
            @PathVariable String paraleloCodigo) {
        servicio.cancelar(estudianteCodigo, paraleloCodigo);
        return ResponseEntity.ok().build();
    }

    private DtoMatricula castDto(Matricula matricula) {
        DtoMatricula dto = new DtoMatricula();
        dto.setEstado(matricula.getEstado());
        dto.setParaleloMateria(matricula.getParaleloMateria());
        dto.setEstudiante(matricula.getEstudiante());
        return dto;
    }

    private Matricula castModelo(DtoMatricula dto) {
        Matricula matricula = new Matricula();
        matricula.setEstado(dto.getEstado());
        matricula.setParaleloMateria(dto.getParaleloMateria());
        matricula.setEstudiante(dto.getEstudiante());
        return matricula;
    }
}
