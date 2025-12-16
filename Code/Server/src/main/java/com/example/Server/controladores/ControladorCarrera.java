package com.example.Server.controladores;
import com.example.Server.casts.CastCarrera;
import com.example.Server.dtos.DtoCarrera;
import com.example.Server.modelos.Carrera;
import com.example.Server.servicios.ServicioCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/carreras")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorCarrera {
    private final ServicioCarrera servicio;
    private final CastCarrera convertidor;

    @PostMapping
    public ResponseEntity<DtoCarrera> crear(@RequestBody DtoCarrera dto) {
        Carrera carrera = convertidor.getModelo(dto);
        Carrera creada = servicio.crear(carrera);
        DtoCarrera respuesta = convertidor.getDto(creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoCarrera>> getCarreras() {
        List<Carrera> carreras = servicio.getCarreras();
        List<DtoCarrera> dtos = new ArrayList<>();

        for (Carrera carrera : carreras)
            dtos.add(convertidor.getDto(carrera));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoCarrera dto) {
        Carrera carrera = convertidor.getModelo(dto);
        servicio.eliminar(carrera);
        return ResponseEntity.ok().build();
    }
}
