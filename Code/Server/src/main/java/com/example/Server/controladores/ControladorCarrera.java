package com.example.Server.controladores;
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

    @PostMapping
    public ResponseEntity<DtoCarrera> crear(@RequestBody DtoCarrera dto) {
        Carrera carrera = castModelo(dto);
        Carrera creada = servicio.crear(carrera);
        DtoCarrera respuesta = castDto(creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoCarrera>> getCarreras() {
        List<Carrera> carreras = servicio.getCarreras();
        List<DtoCarrera> dtos = new ArrayList<>();

        for (Carrera carrera : carreras)
            dtos.add(castDto(carrera));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoCarrera dto) {
        Carrera carrera = castModelo(dto);
        servicio.eliminar(carrera);
        return ResponseEntity.ok().build();
    }

    private Carrera castModelo(DtoCarrera dto) {
        Carrera carrera = new Carrera();
        carrera.setCodigo(dto.getCodigo());
        carrera.setNombre(dto.getNombre());
        return carrera;
    }

    private DtoCarrera castDto(Carrera carrera) {
        DtoCarrera dto = new DtoCarrera();
        dto.setCodigo(carrera.getCodigo());
        dto.setNombre(carrera.getNombre());
        return dto;
    }
}
