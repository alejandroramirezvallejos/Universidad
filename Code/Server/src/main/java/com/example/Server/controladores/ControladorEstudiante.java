package com.example.Server.controladores;

import com.example.Server.casts.CastEstudiante;
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
    private final CastEstudiante convertidor;

    @PostMapping
    public ResponseEntity<DtoEstudiante> crear(@RequestBody DtoEstudiante dto) {
        Estudiante estudiante = convertidor.getModelo(dto);
        Estudiante creado = servicio.crear(estudiante);
        DtoEstudiante respuesta = convertidor.getDto(creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoEstudiante>> getEstudiantes() {
        List<Estudiante> estudiantes = servicio.getEstudiantes();
        List<DtoEstudiante> dtos = new ArrayList<>();

        for (Estudiante estudiante : estudiantes)
            dtos.add(convertidor.getDto(estudiante));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoEstudiante dto) {
        Estudiante estudiante = convertidor.getModelo(dto);
        servicio.eliminar(estudiante);
        return ResponseEntity.ok().build();
    }
}
