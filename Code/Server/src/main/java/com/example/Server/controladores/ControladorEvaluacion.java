package com.example.Server.controladores;
import com.example.Server.dtos.DtoCalificacion;
import com.example.Server.dtos.DtoEvaluacion;
import com.example.Server.modelos.Calificacion;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.servicios.ServicioEvaluacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorEvaluacion {
    private final ServicioEvaluacion servicio;

    @PostMapping
    public ResponseEntity<DtoEvaluacion> crear(@RequestBody DtoEvaluacion dto) {
        Evaluacion evaluacion = servicio.crear(dto.getParaleloMateria(), dto.getNombre(), dto.getPorcentaje());
        DtoEvaluacion respuesta = castDto(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/calificaciones")
    public ResponseEntity<Void> agregarCalificacion(@RequestBody DtoCalificacion dto) {
        Calificacion calificacion = castModelo(dto);
        servicio.agregar(dto.getEvaluacion(), calificacion);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DtoEvaluacion>> getEvaluaciones() {
        List<Evaluacion> evaluaciones = servicio.getEvaluaciones();
        List<DtoEvaluacion> dtos = new ArrayList<>();

        for (Evaluacion evaluacion : evaluaciones)
            dtos.add(castDto(evaluacion));

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoEvaluacion dto) {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setParaleloMateria(dto.getParaleloMateria());
        servicio.eliminar(evaluacion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paralelo/{paraleloCodigo}")
    public ResponseEntity<List<DtoEvaluacion>> obtenerPorParalelo(@PathVariable String paraleloCodigo) {
        List<Evaluacion> evaluaciones = servicio.obtenerPorParalelo(paraleloCodigo);
        List<DtoEvaluacion> dtos = new ArrayList<>();

        for (Evaluacion evaluacion : evaluaciones)
            dtos.add(castDto(evaluacion));

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<DtoCalificacion>> obtenerPorEstudiante(@PathVariable String estudianteCodigo) {
        List<Calificacion> calificaciones = servicio.obtenerCalificacionesEstudiante(estudianteCodigo);
        List<DtoCalificacion> dtos = new ArrayList<>();

        for (Calificacion calificacion : calificaciones) {
            DtoCalificacion dto = new DtoCalificacion();
            dto.setEstudiante(calificacion.getEstudiante());
            dto.setValor(calificacion.getValor());
            dto.setObservaciones(calificacion.getObservaciones());
            dto.setEvaluacion(calificacion.getEvaluacion());
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/calificacion")
    public ResponseEntity<DtoCalificacion> registrarCalificacion(@RequestBody DtoCalificacion dto) {
        Calificacion calificacion = castModelo(dto);
        servicio.agregar(dto.getEvaluacion(), calificacion);
        
        DtoCalificacion respuesta = new DtoCalificacion();
        respuesta.setEstudiante(calificacion.getEstudiante());
        respuesta.setValor(calificacion.getValor());
        respuesta.setObservaciones(calificacion.getObservaciones());
        respuesta.setEvaluacion(dto.getEvaluacion());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    private Calificacion castModelo(DtoCalificacion dto) {
        Calificacion calificacion = new Calificacion();
        calificacion.setEstudiante(dto.getEstudiante());
        calificacion.setValor(dto.getValor());
        calificacion.setObservaciones(dto.getObservaciones());
        return calificacion;
    }

    private DtoEvaluacion castDto(Evaluacion evaluacion) {
        DtoEvaluacion dto = new DtoEvaluacion();
        dto.setParaleloMateria(evaluacion.getParaleloMateria());
        dto.setNombre(evaluacion.getNombre());
        dto.setPorcentaje(evaluacion.getPorcentaje());
        return dto;
    }
}
