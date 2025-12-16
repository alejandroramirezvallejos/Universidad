package com.example.Server.controladores;

import com.example.Server.casts.CastAula;
import com.example.Server.dtos.DtoAula;
import com.example.Server.modelos.Aula;
import com.example.Server.servicios.ServicioAula;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/aulas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorAula {
    private final ServicioAula servicio;
    private final CastAula convertidor;

    @PostMapping
    public ResponseEntity<DtoAula> crear(@RequestBody DtoAula dto) {
        Aula aula = convertidor.getModelo(dto);
        Aula creada = servicio.crear(aula);
        DtoAula respuesta = convertidor.getDto(creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoAula>> getAulas() {
        List<Aula> aulas = servicio.getAulas();
        List<DtoAula> dtos = new ArrayList<>();

        for (Aula aula : aulas)
            dtos.add(convertidor.getDto(aula));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoAula dto) {
        Aula aula = convertidor.getModelo(dto);
        servicio.eliminar(aula);
        return ResponseEntity.ok().build();
    }
}
