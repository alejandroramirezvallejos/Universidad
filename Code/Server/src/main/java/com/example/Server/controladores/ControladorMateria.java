package com.example.Server.controladores;
import com.example.Server.dtos.DtoMateria;
import com.example.Server.dtos.DtoMateriaCarrera;
import com.example.Server.modelos.Materia;
import com.example.Server.servicios.ServicioMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorMateria {
    private final ServicioMateria servicio;

    @PostMapping
    public ResponseEntity<DtoMateria> crear(@RequestBody DtoMateria dto) {
        Materia materia = castModelo(dto);
        Materia creada = servicio.crear(materia);
        DtoMateria respuesta = castDto(creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PostMapping("/agregar-carrera")
    public ResponseEntity<DtoMateria> agregarCarrera(@RequestBody DtoMateriaCarrera dto) {
        Materia materia = servicio.agregarCarrera(dto.getMateria(), dto.getCarrera());
        DtoMateria respuesta = castDto(materia);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoMateria>> getMaterias() {
        List<Materia> materias = servicio.getMaterias();
        List<DtoMateria> dtos = new ArrayList<>();

        for (Materia materia : materias)
            dtos.add(castDto(materia));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoMateria dto) {
        Materia materia = castModelo(dto);
        servicio.eliminar(materia);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<DtoMateria> actualizar(@PathVariable String codigo, @RequestBody DtoMateria dto) {
        Materia materia = servicio.buscarPorCodigo(codigo);

        if (materia == null)
            return ResponseEntity.notFound().build();

        materia.setNombre(dto.getNombre());
        materia.setSemestre(dto.getSemestre());
        materia.setCreditos(dto.getCreditos());
        
        Materia actualizada = servicio.actualizar(materia);
        return ResponseEntity.ok(castDto(actualizada));
    }

    @PatchMapping("/{codigo}/estado")
    public ResponseEntity<DtoMateria> cambiarEstado(@PathVariable String codigo) {
        Materia materia = servicio.buscarPorCodigo(codigo);

        if (materia == null)
            return ResponseEntity.notFound().build();

        materia.setActiva(!materia.isActiva());
        Materia actualizada = servicio.actualizar(materia);
        
        return ResponseEntity.ok(castDto(actualizada));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DtoMateria> obtenerPorCodigo(@PathVariable String codigo) {
        Materia materia = servicio.buscarPorCodigo(codigo);

        if (materia == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(castDto(materia));
    }

    private Materia castModelo(DtoMateria dto) {
        Materia materia = new Materia();
        materia.setCodigo(dto.getCodigo());
        materia.setNombre(dto.getNombre());
        materia.setSemestre(dto.getSemestre());
        materia.setCreditos(dto.getCreditos());
        return materia;
    }

    private DtoMateria castDto(Materia materia) {
        DtoMateria dto = new DtoMateria();
        dto.setCodigo(materia.getCodigo());
        dto.setNombre(materia.getNombre());
        dto.setSemestre(materia.getSemestre());
        dto.setCreditos(materia.getCreditos());
        return dto;
    }
}
