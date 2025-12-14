package com.example.Server.controladores;
import com.example.Server.dtos.DtoHistorialAcademico;
import com.example.Server.dtos.DtoPromedio;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.HistorialAcademico;
import com.example.Server.servicios.ServicioHistorialAcademico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorHistorialAcademico {
    private final ServicioHistorialAcademico servicio;

    @PostMapping
    public ResponseEntity<DtoHistorialAcademico> crear(@RequestBody DtoHistorialAcademico dto) {
        Estudiante estudiante = castModelo(dto);
        HistorialAcademico historial = servicio.crear(estudiante);
        DtoHistorialAcademico respuesta = castDto(historial);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DtoHistorialAcademico>> getHistoriales() {
        List<HistorialAcademico> historiales = servicio.getHistoriales();
        List<DtoHistorialAcademico> dtos = new ArrayList<>();

        for (HistorialAcademico historial : historiales)
            dtos.add(castDto(historial));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoHistorialAcademico dto) {
        Estudiante estudiante = castModelo(dto);
        HistorialAcademico historial = new HistorialAcademico();
        historial.setEstudiante(estudiante);
        servicio.eliminar(historial);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<DtoHistorialAcademico> obtenerPorEstudiante(@PathVariable String estudianteCodigo) {
        HistorialAcademico historial = servicio.obtenerPorEstudiante(estudianteCodigo);
        if (historial == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(castDto(historial));
    }

    @GetMapping("/estudiante/{estudianteCodigo}/promedio")
    public ResponseEntity<DtoPromedio> obtenerPromedio(@PathVariable String estudianteCodigo) {
        Double promedio = servicio.calcularPromedioGeneral(estudianteCodigo);
        
        DtoPromedio dto = new DtoPromedio();
        dto.setEstudianteCodigo(estudianteCodigo);
        dto.setPromedio(promedio);
        dto.setTotalMaterias(0); // TODO: calcular total de materias
        
        return ResponseEntity.ok(dto);
    }

    private Estudiante castModelo(DtoHistorialAcademico dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo(dto.getCodigoEstudiante());
        estudiante.setNombre(dto.getNombreEstudiante());
        return estudiante;
    }

    private DtoHistorialAcademico castDto(HistorialAcademico historial) {
        DtoHistorialAcademico dto = new DtoHistorialAcademico();
        dto.setCodigoEstudiante(historial.getEstudiante().getCodigo());
        dto.setNombreEstudiante(historial.getEstudiante().getNombre());
        return dto;
    }
}
