package com.example.Server.controladores;
import com.example.Server.dtos.DtoDocente;
import com.example.Server.modelos.Docente;
import com.example.Server.servicios.ServicioDocente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/docentes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorDocente {
    private final ServicioDocente servicio;

    @PostMapping
    public ResponseEntity<DtoDocente> crear(@RequestBody DtoDocente dto) {
        Docente docente = castModelo(dto);
        Docente creado = servicio.crear(docente);
        DtoDocente respuesta = castDto(creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoDocente>> getDocentes() {
        List<Docente> docentes = servicio.getDocentes();
        List<DtoDocente> dtos = new ArrayList<>();

        for (Docente docente : docentes)
            dtos.add(castDto(docente));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoDocente dto) {
        Docente docente = castModelo(dto);
        servicio.eliminar(docente);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activos")
    public ResponseEntity<List<DtoDocente>> getDocentesActivos() {
        List<Docente> docentes = servicio.getDocentesActivos();
        List<DtoDocente> dtos = new ArrayList<>();

        for (Docente docente : docentes)
            dtos.add(castDto(docente));

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DtoDocente> obtenerPorCodigo(@PathVariable String codigo) {
        Docente docente = servicio.buscarPorCodigo(codigo);
        if (docente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(castDto(docente));
    }

    private Docente castModelo(DtoDocente dto) {
        Docente docente = new Docente();
        docente.setCodigo(dto.getCodigo());
        docente.setNombre(dto.getNombre());
        docente.setEspecialidad(dto.getEspecialidad());
        return docente;
    }

    private DtoDocente castDto(Docente docente) {
        DtoDocente dto = new DtoDocente();
        dto.setCodigo(docente.getCodigo());
        dto.setNombre(docente.getNombre());
        dto.setEspecialidad(docente.getEspecialidad());
        return dto;
    }
}
