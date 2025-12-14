package com.example.Server.controladores;
import com.example.Server.dtos.DtoDirectorCarrera;
import com.example.Server.modelos.DirectorCarrera;
import com.example.Server.servicios.ServicioDirectorCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador REST para gestionar DirectorCarrera
 * Expone endpoint /api/directores
 */
@RestController
@RequestMapping("/api/directores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorDirectorCarrera {
    private final ServicioDirectorCarrera servicio;

    @PostMapping
    public ResponseEntity<DtoDirectorCarrera> crear(@RequestBody DtoDirectorCarrera dto) {
        DirectorCarrera director = castModelo(dto);
        DirectorCarrera creado = servicio.crear(director);
        DtoDirectorCarrera respuesta = castDto(creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoDirectorCarrera>> getDirectores() {
        List<DirectorCarrera> directores = servicio.getDirectores();
        List<DtoDirectorCarrera> dtos = new ArrayList<>();

        for (DirectorCarrera director : directores)
            dtos.add(castDto(director));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoDirectorCarrera dto) {
        DirectorCarrera director = castModelo(dto);
        servicio.eliminar(director);
        return ResponseEntity.ok().build();
    }

    private DirectorCarrera castModelo(DtoDirectorCarrera dto) {
        DirectorCarrera director = new DirectorCarrera();
        director.setCodigo(dto.getCodigo());
        // Separar nombre completo en nombre y apellido
        String[] partes = dto.getNombre().split(" ", 2);
        director.setNombre(partes.length > 0 ? partes[0] : dto.getNombre());
        director.setApellido(partes.length > 1 ? partes[1] : "");
        director.setEmail(dto.getEmail());
        return director;
    }

    private DtoDirectorCarrera castDto(DirectorCarrera director) {
        DtoDirectorCarrera dto = new DtoDirectorCarrera();
        dto.setCodigo(director.getCodigo());
        dto.setNombre(director.getNombre() + " " + director.getApellido());
        dto.setEmail(director.getEmail());
        return dto;
    }
}
