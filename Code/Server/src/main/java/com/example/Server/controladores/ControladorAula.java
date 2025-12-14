package com.example.Server.controladores;
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

    @PostMapping
    public ResponseEntity<DtoAula> crear(@RequestBody DtoAula dto) {
        Aula aula = castModelo(dto);
        Aula creada = servicio.crear(aula);
        DtoAula respuesta = castDto(creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoAula>> getAulas() {
        List<Aula> aulas = servicio.getAulas();
        List<DtoAula> dtos = new ArrayList<>();

        for (Aula aula : aulas)
            dtos.add(castDto(aula));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoAula dto) {
        Aula aula = castModelo(dto);
        servicio.eliminar(aula);
        return ResponseEntity.ok().build();
    }

    private Aula castModelo(DtoAula dto) {
        Aula aula = new Aula();
        aula.setCodigo(dto.getCodigo());
        aula.setEdificio(dto.getEdificio());
        aula.setCapacidad(dto.getCapacidad());
        aula.setDisponible(dto.isDisponible());
        return aula;
    }

    private DtoAula castDto(Aula aula) {
        DtoAula dto = new DtoAula();
        dto.setCodigo(aula.getCodigo());
        dto.setEdificio(aula.getEdificio());
        dto.setCapacidad(aula.getCapacidad());
        dto.setDisponible(aula.isDisponible());
        return dto;
    }
}
