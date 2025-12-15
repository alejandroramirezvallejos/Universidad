package com.example.Server.controladores;
import com.example.Server.dtos.DtoParaleloMateria;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.servicios.ServicioParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/paralelos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorParaleloMateria {
    private final ServicioParaleloMateria servicio;

    @PostMapping
    public ResponseEntity<DtoParaleloMateria> crear(@RequestBody DtoParaleloMateria dto) {
        ParaleloMateria paralelo = castModelo(dto);
        ParaleloMateria creado = servicio.crear(paralelo);
        DtoParaleloMateria respuesta = castDto(creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoParaleloMateria>> getParalelos() {
        List<ParaleloMateria> paralelos = servicio.getParalelos();
        List<DtoParaleloMateria> dtos = new ArrayList<>();

        for (ParaleloMateria paralelo : paralelos)
            dtos.add(castDto(paralelo));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoParaleloMateria dto) {
        ParaleloMateria paralelo = castModelo(dto);
        servicio.eliminar(paralelo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<DtoParaleloMateria> actualizar(
            @PathVariable String codigo,
            @RequestBody DtoParaleloMateria dto) {
        ParaleloMateria paralelo = servicio.buscarPorCodigo(codigo);

        if (paralelo == null)
            return ResponseEntity.notFound().build();

        paralelo.setMateria(dto.getMateria());
        paralelo.setDocente(dto.getDocente());
        paralelo.setAula(dto.getAula());
        paralelo.setCupoMaximo(dto.getCupoMaximo());

        if (dto.getHorarios() != null)
            paralelo.setHorarios(dto.getHorarios());

        ParaleloMateria actualizado = servicio.actualizar(paralelo);

        return ResponseEntity.ok(castDto(actualizado));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DtoParaleloMateria> obtenerPorCodigo(@PathVariable String codigo) {
        ParaleloMateria paralelo = servicio.buscarPorCodigo(codigo);

        if (paralelo == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(castDto(paralelo));
    }

    @GetMapping("/docente/{docenteCodigo}")
    public ResponseEntity<List<DtoParaleloMateria>> obtenerPorDocente(@PathVariable String docenteCodigo) {
        List<ParaleloMateria> paralelos = servicio.obtenerPorDocente(docenteCodigo);
        List<DtoParaleloMateria> dtos = new ArrayList<>();

        for (ParaleloMateria paralelo : paralelos)
            dtos.add(castDto(paralelo));

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/materia/{materiaCodigo}")
    public ResponseEntity<List<DtoParaleloMateria>> obtenerPorMateria(@PathVariable String materiaCodigo) {
        List<ParaleloMateria> paralelos = servicio.obtenerPorMateria(materiaCodigo);
        List<DtoParaleloMateria> dtos = new ArrayList<>();

        for (ParaleloMateria paralelo : paralelos)
            dtos.add(castDto(paralelo));

        return ResponseEntity.ok(dtos);
    }

    private ParaleloMateria castModelo(DtoParaleloMateria dto) {
        ParaleloMateria paralelo = new ParaleloMateria();
        paralelo.setCodigo(dto.getCodigo());
        paralelo.setMateria(dto.getMateria());
        paralelo.setDocente(dto.getDocente());
        paralelo.setAula(dto.getAula());
        paralelo.setCupoMaximo(dto.getCupoMaximo());
        paralelo.setEstudiantes(new ArrayList<>());
        paralelo.setHorarios(dto.getHorarios() != null ? dto.getHorarios() : new ArrayList<>());
        return paralelo;
    }

    private DtoParaleloMateria castDto(ParaleloMateria paralelo) {
        DtoParaleloMateria dto = new DtoParaleloMateria();
        dto.setCodigo(paralelo.getCodigo());
        dto.setMateria(paralelo.getMateria());
        dto.setDocente(paralelo.getDocente());
        dto.setAula(paralelo.getAula());
        dto.setCupoMaximo(paralelo.getCupoMaximo());
        dto.setHorarios(paralelo.getHorarios());
        return dto;
    }
}

