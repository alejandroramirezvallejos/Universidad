package com.example.Server.controladores;
import com.example.Server.dtos.DtoGestion;
import com.example.Server.modelos.Gestion;
import com.example.Server.servicios.ServicioGestion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gestiones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorGestion {
    private final ServicioGestion servicio;
    private final SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping
    public ResponseEntity<DtoGestion> crear(@RequestBody DtoGestion dto) {
        try {
            Gestion gestion = castModelo(dto);
            Gestion creada = servicio.crear(gestion);
            DtoGestion respuesta = castDto(creada);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }
        catch (Exception excepcion) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DtoGestion>> getGestiones() {
        List<Gestion> gestiones = servicio.getGestiones();
        List<DtoGestion> dtos = new ArrayList<>();

        for (Gestion gestion : gestiones)
            dtos.add(castDto(gestion));

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/actual")
    public ResponseEntity<DtoGestion> getGestionActual() {
        List<Gestion> gestiones = new ArrayList<>();
        Optional<Gestion> gestionActual = servicio.getGestionActual();

        if (gestionActual.isPresent())
            gestiones.add(gestionActual.get());

        for (Gestion gestion : gestiones)
            return ResponseEntity.ok(castDto(gestion));

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DtoGestion> getPorCodigo(@PathVariable String codigo) {
        List<Gestion> gestiones = new ArrayList<>();
        Optional<Gestion> gestionActual = servicio.buscarPorCodigo(codigo);

        if (gestionActual.isPresent())
            gestiones.add(gestionActual.get());

        for (Gestion gestion : gestiones)
            return ResponseEntity.ok(castDto(gestion));

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoGestion dto) {
        try {
            Gestion gestion = castModelo(dto);
            servicio.eliminar(gestion);
            return ResponseEntity.ok().build();
        }
        catch (Exception excepcion) {
            return ResponseEntity.badRequest().build();
        }
    }

    private Gestion castModelo(DtoGestion dto) throws Exception {
        Gestion gestion = new Gestion();
        gestion.setCodigo(dto.getCodigo());
        gestion.setNombre(dto.getNombre());
        gestion.setAnio(dto.getAnio());
        gestion.setPeriodo(dto.getPeriodo());
        gestion.setEstado(dto.getEstado());
        
        if (dto.getFechaInicio() != null)
            gestion.setFechaInicio(fecha.parse(dto.getFechaInicio()));
        if (dto.getFechaFin() != null)
            gestion.setFechaFin(fecha.parse(dto.getFechaFin()));
        if (dto.getFechaInicioMatricula() != null)
            gestion.setFechaInicioMatricula(fecha.parse(dto.getFechaInicioMatricula()));
        if (dto.getFechaFinMatricula() != null)
            gestion.setFechaFinMatricula(fecha.parse(dto.getFechaFinMatricula()));

        return gestion;
    }

    private DtoGestion castDto(Gestion gestion) {
        DtoGestion dto = new DtoGestion();
        dto.setCodigo(gestion.getCodigo());
        dto.setNombre(gestion.getNombre());
        dto.setAnio(gestion.getAnio());
        dto.setPeriodo(gestion.getPeriodo());
        dto.setEstado(gestion.getEstado());
        
        if (gestion.getFechaInicio() != null)
            dto.setFechaInicio(fecha.format(gestion.getFechaInicio()));
        if (gestion.getFechaFin() != null)
            dto.setFechaFin(fecha.format(gestion.getFechaFin()));
        if (gestion.getFechaInicioMatricula() != null)
            dto.setFechaInicioMatricula(fecha.format(gestion.getFechaInicioMatricula()));
        if (gestion.getFechaFinMatricula() != null)
            dto.setFechaFinMatricula(fecha.format(gestion.getFechaFinMatricula()));

        return dto;
    }
}
