package com.example.Server.controladores;
import com.example.Server.dtos.DtoEstudiante;
import com.example.Server.modelos.Estudiante;
import com.example.Server.servicios.ServicioEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorEstudiante {
    private final ServicioEstudiante servicio;

    @PostMapping
    public ResponseEntity<DtoEstudiante> crear(@RequestBody DtoEstudiante dto) {
        Estudiante estudiante = castModelo(dto);
        Estudiante creado = servicio.crear(estudiante);
        DtoEstudiante respuesta = castDto(creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoEstudiante>> getEstudiantes() {
        List<Estudiante> estudiantes = servicio.getEstudiantes();
        List<DtoEstudiante> dtos = new ArrayList<>();

        for (Estudiante estudiante : estudiantes)
            dtos.add(castDto(estudiante));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoEstudiante dto) {
        Estudiante estudiante = castModelo(dto);
        servicio.eliminar(estudiante);
        return ResponseEntity.ok().build();
    }

    private Estudiante castModelo(DtoEstudiante dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo(dto.getCodigo());
        estudiante.setNombre(dto.getNombre());
        estudiante.setEmail(dto.getEmail());
        return estudiante;
    }

    private DtoEstudiante castDto(Estudiante estudiante) {
        DtoEstudiante dto = new DtoEstudiante();
        dto.setCodigo(estudiante.getCodigo());
        dto.setNombre(estudiante.getNombre());
        dto.setEmail(estudiante.getEmail());
        return dto;
    }
}
