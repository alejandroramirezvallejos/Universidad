package com.example.Server.controladores;

import com.example.Server.casts.CastDirector;
import com.example.Server.dtos.DtoDirectorCarrera;
import com.example.Server.modelos.DirectorCarrera;
import com.example.Server.servicios.ServicioDirectorCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/directores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorDirectorCarrera {
    private final ServicioDirectorCarrera servicio;
    private final CastDirector convertidor;

    @PostMapping
    public ResponseEntity<DtoDirectorCarrera> crear(@RequestBody DtoDirectorCarrera dto) {
        DirectorCarrera director = convertidor.getModelo(dto);
        DirectorCarrera creado = servicio.crear(director);
        DtoDirectorCarrera respuesta = convertidor.getDto(creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoDirectorCarrera>> getDirectores() {
        List<DirectorCarrera> directores = servicio.getDirectores();
        List<DtoDirectorCarrera> dtos = new ArrayList<>();

        for (DirectorCarrera director : directores)
            dtos.add(convertidor.getDto(director));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoDirectorCarrera dto) {
        DirectorCarrera director = convertidor.getModelo(dto);
        servicio.eliminar(director);
        return ResponseEntity.ok().build();
    }
}
