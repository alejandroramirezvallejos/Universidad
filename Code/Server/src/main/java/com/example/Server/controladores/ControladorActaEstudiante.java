package com.example.Server.controladores;
import com.example.Server.dtos.DtoActaEstudiante;
import com.example.Server.dtos.DtoNotificacion;
import com.example.Server.modelos.ActaEstudiante;
import com.example.Server.servicios.ServicioActaEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/actas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorActaEstudiante {
    private final ServicioActaEstudiante servicio;

    @PostMapping
    public ResponseEntity<DtoActaEstudiante> crear(@RequestBody DtoActaEstudiante dto) {
        ActaEstudiante acta = servicio.crear(dto.getEstudiante(), dto.getParaleloMateria());
        DtoActaEstudiante respuesta = castDto(acta);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoActaEstudiante>> getActas() {
        List<ActaEstudiante> actas = servicio.getActas();
        List<DtoActaEstudiante> dtos = new ArrayList<>();

        for (ActaEstudiante acta : actas)
            dtos.add(castDto(acta));

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/aprobadas")
    public ResponseEntity<List<DtoActaEstudiante>> getActasAprobadas() {
        List<ActaEstudiante> actas = servicio.getActasAprobadas();
        List<DtoActaEstudiante> dtos = new ArrayList<>();

        for (ActaEstudiante acta : actas)
            dtos.add(castDto(acta));

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/reprobadas")
    public ResponseEntity<List<DtoActaEstudiante>> getActasReprobadas() {
        List<ActaEstudiante> actas = servicio.getActasReprobadas();
        List<DtoActaEstudiante> dtos = new ArrayList<>();

        for (ActaEstudiante acta : actas)
            dtos.add(castDto(acta));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoActaEstudiante dto) {
        ActaEstudiante acta = castModelo(dto);
        servicio.eliminar(acta);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notificar")
    public ResponseEntity<Void> notificar(@RequestBody DtoNotificacion dto) {
        servicio.notificar(dto.getEstudiante(), dto.getMateria(), dto.getNotaFinal());
        return ResponseEntity.ok().build();
    }

    private DtoActaEstudiante castDto(ActaEstudiante acta) {
        DtoActaEstudiante dto = new DtoActaEstudiante();
        dto.setEstudiante(acta.getEstudiante());
        dto.setParaleloMateria(acta.getParaleloMateria());
        return dto;
    }

    private ActaEstudiante castModelo(DtoActaEstudiante dto) {
        ActaEstudiante acta = new ActaEstudiante();
        acta.setEstudiante(dto.getEstudiante());
        acta.setParaleloMateria(dto.getParaleloMateria());
        return acta;
    }
}
